package com.fido.domain;

import java.io.Serializable;
import java.util.HashMap;

public class MapAndData implements Serializable{
	private HashMap<Character, Integer> map;// ����ַ�Ƶ��
	private String data;// ��Ŷ�ȡ�������ı�

	// �޲ι���
	public MapAndData() {

	}

	public MapAndData(HashMap<Character, Integer> map, String data) {
		this.map = map;
		this.data = data;
	}

	public HashMap<Character, Integer> getMap() {
		return map;
	}

	public void setMap(HashMap<Character, Integer> map) {
		this.map = map;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}


 

}
