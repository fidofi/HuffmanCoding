package com.fido.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author: fido
 * @date:2017年5月16日 下午7:14:53
 * @description：用于边读取原文本的文件,利用构造好的编码表,边将压缩后的数据写入新的文件
 * 
 */
public final class BinaryOutUtils {
	private static BufferedOutputStream out;

	public BinaryOutUtils(BufferedOutputStream out) {
		this.out = out;
	}

	public BinaryOutUtils() {
	}

	public void write2local(String str) {
		int strLength = str.length();// 传入的01字符串长度
		int byteLength = strLength / 8;// 刚好是8的倍数,数组的长度等于共有的位数/8
		int[] bb = new int[byteLength];
		List<Integer> list = new ArrayList();
		for (int i = 0; i < byteLength; i++) {
			for (int j = 0; j < str.length(); j = j + 8) {
				list.add(Integer.parseInt(str.substring(j, j + 8), 2));
			}
			bb[i] = (Integer) list.get(i);
			try {
				this.getBufferOut().write(bb[i]);
			} catch (IOException e) {
				System.out.println("读取文件并写将压缩数据写入新文件时出错");
			}
		}
	}

	public BufferedOutputStream getBufferOut() {
		return out;
	}

	public void setBufferOut(BufferedOutputStream bufferOut) {
		this.out = bufferOut;
	}

}
