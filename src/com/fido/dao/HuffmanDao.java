package com.fido.dao;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fido.domain.HuffmanTree;
import com.fido.domain.Node;
import com.fido.utils.ArticleUtils;
import com.fido.utils.BinaryOutUtils;
import com.fido.utils.FidoUtils;
import com.fido.utils.TreeUtils;
import com.fido.utils.TwoBinaryUtils;

public class HuffmanDao {

	// 读取要编码的文件,生成编码后的文件,返回是否成功
	public void encoding(String path, String newPath) throws IOException {
		HashMap<Character, Integer> hashMap = ArticleUtils.readAndCount(path)
				.getMap();// 获得字符和对应频数
		List<Node> list = FidoUtils.getNodeList(hashMap);// 放进List里
		HuffmanTree tree = new HuffmanTree(list);// 构造对应的哈夫曼树
		HashMap<Character, String> encodingMap = TreeUtils.encodingMap(tree);// 编码表
		File fileOut = new File(newPath);// 压缩数据存放的文本(第一次写，为了写入编码表)
		FileOutputStream ffout = new FileOutputStream(fileOut);
		File fileOut_two = new File(newPath);// 第二次写,为了写入数据
		FileOutputStream ffout_two = new FileOutputStream(fileOut_two, true);
		BufferedOutputStream bufferOut = new BufferedOutputStream(ffout_two);
		BinaryOutUtils out = new BinaryOutUtils(bufferOut);// 写入压缩数据的输出流
		ObjectOutputStream objectOut = new ObjectOutputStream(ffout);// 把编码表也写入文件方便解码
		objectOut.writeObject(encodingMap);
		objectOut.flush();
		objectOut.close();
		// 读原始文本
		File fileIn = new File(path);
		StringBuffer bufferStr = new StringBuffer();
		BufferedReader reader = new BufferedReader(new FileReader(fileIn));
		try {
			int aa;// 表示读取到的字符
			while ((aa = reader.read()) != -1) {// 未到文件末尾
				char temp = (char) aa;// 当前读到的单个字符
				String str = encodingMap.get(temp);// 获得当前字符对应的01编码串
				bufferStr.append(str);
				if (bufferStr.length() % 8 == 0) {// 满8的倍数位了
					out.write2local(bufferStr.toString());// 转为int写入新文件
					bufferStr.delete(0, bufferStr.length());
				}
			}
			System.out.println("缓存中还有的字符长度："+bufferStr.length());
			if (bufferStr.length() != 0) {
				int[] otherData = TwoBinaryUtils.str2int(bufferStr.toString());
				for (int i = 0; i < otherData.length; i++) {
					System.out.print(otherData[i] + " ");
					out.getBufferOut().write(otherData[i]);
				}
				out.getBufferOut().flush();
			}
			else{ //说明补零个数为0
				
				out.getBufferOut().write(0);
				out.getBufferOut().flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("边读边写的时候发生异常!");
		} finally {
			reader.close();
			out.getBufferOut().close();
		}
	}

	public void decoding(String path, String newPath) throws IOException,
			ClassNotFoundException {
		HashMap<String, Character> decodingMap = new HashMap<String, Character>();
		File file2in = new File(path); // 待解码文件
		FileInputStream fileIn = new FileInputStream(file2in);
		int aa;// 代表读到的整数
		ObjectInputStream objectIn = new ObjectInputStream(fileIn);// 用来读编码表的
		HashMap<Character, String> encodingMap = (HashMap<Character, String>) objectIn
				.readObject();// 得到编码表
		ArrayList<String> keyList = new ArrayList<String>();
		ArrayList<Character> valueList = new ArrayList<Character>();
		for (Map.Entry<Character, String> entry : encodingMap.entrySet()) {
			keyList.add(entry.getValue());
			valueList.add(entry.getKey());
		}
		for (int i = 0; i < keyList.size(); i++) {
			decodingMap.put(keyList.get(i), valueList.get(i));
		}
		File file2out = new File(newPath);
		FileOutputStream fileOut = new FileOutputStream(file2out);
		BufferedOutputStream out = new BufferedOutputStream(fileOut);
		StringBuffer buffer = new StringBuffer();
		StringBuffer ss = new StringBuffer();// 待比较
		while ((aa = fileIn.read()) != -1) { // 这里如果用objectIn去读会错
			String str = TwoBinaryUtils.int2twobinary(aa);// 将读到的整数转为8位二进制序列
			if (fileIn.available() != 0) {
				buffer = buffer.append(str);
			}
			if (fileIn.available() <= 1) { // 读到最后两个整数(因为还要考虑到补零个数的情况，补零个数不会超过7个
				if (fileIn.available() == 0) {
					int zero = aa;// 补零个数
					System.out.println("补零个数："+zero);
					str = buffer.substring(0, buffer.length() - zero);
					StringBuffer buffer_two = new StringBuffer();
					for (int k = 0; k < str.length(); k++) {
						buffer_two.append(str.charAt(k));
						if (decodingMap.containsKey(buffer_two.toString())) {
							out.write(decodingMap.get(buffer_two.toString()));
							buffer_two.delete(0, buffer_two.length());
						}
					}
				}
			} else {
				for (int i = 0; i < buffer.length(); i++) {
					ss = ss.append(buffer.charAt(i));
					if (decodingMap.containsKey(ss.toString())) {
						out.write(decodingMap.get(ss.toString()));// 把查找编码表后的字符写入新文件
						ss.delete(0, ss.length());
					}
				}
				int current = ss.length();
				ss.delete(0, ss.length());
				buffer.delete(0, buffer.length() - (current - 1) - 1);
			}
		}
		out.flush();
		out.close();
	}

	// 计算压缩比
	public String count(String path, String newPath) {
		FileChannel before = null;
		FileChannel after = null;
		File beforeFile = new File(path);
		File afterFile = new File(newPath);
		FileInputStream beforeIn = null;
		FileInputStream afterIn = null;
		String str = null;
		try {
			if ((beforeFile.isFile() && beforeFile.exists())
					&& (afterFile.isFile() && afterFile.exists())) {
				beforeIn = new FileInputStream(beforeFile);
				afterIn = new FileInputStream(afterFile);
				before = beforeIn.getChannel();
				after = afterIn.getChannel();
				long beforeSize = before.size();
				long afterSize = after.size();
				double countSize = (double) beforeSize / (double) afterSize;
				str = String.valueOf(countSize);
				return str;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("计算压缩比文件未找到");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("计算文件大小的时候出错");
		} finally {
			try {
				beforeIn.close();
				afterIn.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("计算压缩比时关闭流出现问题");
			}
		}
		return str;
	}
}

