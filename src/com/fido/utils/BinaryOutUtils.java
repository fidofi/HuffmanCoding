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
 * @date:2017��5��16�� ����7:14:53
 * @description�����ڱ߶�ȡԭ�ı����ļ�,���ù���õı����,�߽�ѹ���������д���µ��ļ�
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
		int strLength = str.length();// �����01�ַ�������
		int byteLength = strLength / 8;// �պ���8�ı���,����ĳ��ȵ��ڹ��е�λ��/8
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
				System.out.println("��ȡ�ļ���д��ѹ������д�����ļ�ʱ����");
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
