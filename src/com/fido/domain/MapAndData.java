package com.fido.domain;

import java.io.Serializable;
import java.util.HashMap;

/*�տ�ʼ��˼�е��ң���������������ı�Ҳ������������У�
û�п��ǵ��ı�����ܶ�����������Ҳ����Ҫ�õ�ԭ���ı�����Ϣ�����Ժ�����������ɾ��*/
public class MapAndData implements Serializable{
	private HashMap<Character, Integer> map;// ����ַ�Ƶ��
	//private String data;

	// �޲ι���
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
