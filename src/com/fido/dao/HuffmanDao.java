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

	// ��ȡҪ������ļ�,���ɱ������ļ�,�����Ƿ�ɹ�
	public void encoding(String path, String newPath) throws IOException {
		HashMap<Character, Integer> hashMap = ArticleUtils.readAndCount(path)
				.getMap();// ����ַ��Ͷ�ӦƵ��
		List<Node> list = FidoUtils.getNodeList(hashMap);// �Ž�List��
		HuffmanTree tree = new HuffmanTree(list);// �����Ӧ�Ĺ�������
		HashMap<Character, String> encodingMap = TreeUtils.encodingMap(tree);// �����
		File fileOut = new File(newPath);// ѹ�����ݴ�ŵ��ı�(��һ��д��Ϊ��д������)
		FileOutputStream ffout = new FileOutputStream(fileOut);
		File fileOut_two = new File(newPath);// �ڶ���д,Ϊ��д������
		FileOutputStream ffout_two = new FileOutputStream(fileOut_two, true);
		BufferedOutputStream bufferOut = new BufferedOutputStream(ffout_two);
		BinaryOutUtils out = new BinaryOutUtils(bufferOut);// д��ѹ�����ݵ������
		ObjectOutputStream objectOut = new ObjectOutputStream(ffout);// �ѱ����Ҳд���ļ��������
		objectOut.writeObject(encodingMap);
		objectOut.flush();
		objectOut.close();
		// ��ԭʼ�ı�
		File fileIn = new File(path);
		StringBuffer bufferStr = new StringBuffer();
		BufferedReader reader = new BufferedReader(new FileReader(fileIn));
		try {
			int aa;// ��ʾ��ȡ�����ַ�
			while ((aa = reader.read()) != -1) {// δ���ļ�ĩβ
				char temp = (char) aa;// ��ǰ�����ĵ����ַ�
				String str = encodingMap.get(temp);// ��õ�ǰ�ַ���Ӧ��01���봮
				bufferStr.append(str);
				if (bufferStr.length() % 8 == 0) {// ��8�ı���λ��
					out.write2local(bufferStr.toString());// תΪintд�����ļ�
					bufferStr.delete(0, bufferStr.length());
				}
			}
			System.out.println("�����л��е��ַ����ȣ�"+bufferStr.length());
			if (bufferStr.length() != 0) {
				int[] otherData = TwoBinaryUtils.str2int(bufferStr.toString());
				for (int i = 0; i < otherData.length; i++) {
					System.out.print(otherData[i] + " ");
					out.getBufferOut().write(otherData[i]);
				}
				out.getBufferOut().flush();
			}
			else{ //˵���������Ϊ0
				
				out.getBufferOut().write(0);
				out.getBufferOut().flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�߶���д��ʱ�����쳣!");
		} finally {
			reader.close();
			out.getBufferOut().close();
		}
	}

	public void decoding(String path, String newPath) throws IOException,
			ClassNotFoundException {
		HashMap<String, Character> decodingMap = new HashMap<String, Character>();
		File file2in = new File(path); // �������ļ�
		FileInputStream fileIn = new FileInputStream(file2in);
		int aa;// �������������
		ObjectInputStream objectIn = new ObjectInputStream(fileIn);// ������������
		HashMap<Character, String> encodingMap = (HashMap<Character, String>) objectIn
				.readObject();// �õ������
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
		StringBuffer ss = new StringBuffer();// ���Ƚ�
		while ((aa = fileIn.read()) != -1) { // ���������objectInȥ�����
			String str = TwoBinaryUtils.int2twobinary(aa);// ������������תΪ8λ����������
			if (fileIn.available() != 0) {
				buffer = buffer.append(str);
			}
			if (fileIn.available() <= 1) { // ���������������(��Ϊ��Ҫ���ǵ�������������������������ᳬ��7��
				if (fileIn.available() == 0) {
					int zero = aa;// �������
					System.out.println("���������"+zero);
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
						out.write(decodingMap.get(ss.toString()));// �Ѳ��ұ�������ַ�д�����ļ�
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

	// ����ѹ����
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
			System.out.println("����ѹ�����ļ�δ�ҵ�");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("�����ļ���С��ʱ�����");
		} finally {
			try {
				beforeIn.close();
				afterIn.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("����ѹ����ʱ�ر�����������");
			}
		}
		return str;
	}
}

