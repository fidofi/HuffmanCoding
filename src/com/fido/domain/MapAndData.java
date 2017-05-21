package com.fido.domain;

import java.io.Serializable;
import java.util.HashMap;

public class MapAndData implements Serializable{
	private HashMap<Character, Integer> map;// 存放字符频数
	private String data;// 存放读取出来的文本

	// 无参构造
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
