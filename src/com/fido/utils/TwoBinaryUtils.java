package com.fido.utils;

import java.util.ArrayList;
import java.util.List;

public class TwoBinaryUtils {
	public static int[] str2int(String str) {
		int strLength = str.length();// �����01�ַ�������
		int byteLength = 0;// ���ص�����ĳ���
		int temp = strLength % 8;// 8λΪһ���ֽ�
		String newStr = null;
		StringBuffer buffer = new StringBuffer(str);// ��������8λ��0����
		if (temp == 0) {
			byteLength = strLength / 8;// �պ���8�ı���,����ĳ��ȵ��ڹ��е�λ��/8
		} else {
			byteLength = strLength / 8 + 1;
			for (int i = 0; i < 8 - (temp); i++) { // ������0
				buffer.append("0");
			}

		}
		int[] bb = new int[byteLength + 1];
		List list = new ArrayList();
		for (int i = 0; i < byteLength; i++) {
			for (int j = 0; j < buffer.length(); j = j + 8) {
				list.add(Integer.parseInt(buffer.substring(j, j + 8), 2));
			}
			bb[i] = (Integer) list.get(i);
		}
		bb[byteLength] = 8 - temp;// �����������һλ�Ų�0�ĸ���
		System.out.println("�������Ϊ��"+bb[byteLength]);
		return bb;
	}

	public static String int2twobinary(int value) {
		String s = "";
		for (int i = 0; i < 8; i++) {
			s = value % 2 + s;
			value = value / 2;
		}
		return s;
	}
}
