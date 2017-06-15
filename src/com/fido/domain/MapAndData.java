package com.fido.domain;

import java.io.Serializable;
import java.util.HashMap;

/*刚开始构思有点乱，本来打算读到的文本也放入这个对象中，
没有考虑到文本如果很多的情况，而且也不需要用到原有文本的信息，所以后面把这个属性删掉*/
public class MapAndData implements Serializable{
	private HashMap<Character, Integer> map;// 存放字符频数
	//private String data;

	// 无参构造
	public MapAndData() {

	}

	public MapAndData(HashMap<Character, Integer> map) {
		this.map = map;
	}

	public HashMap<Character, Integer> getMap() {
		return map;
	}

	public void setMap(HashMap<Character, Integer> map) {
		this.map = map;
	}


 

}
