package com.fido.utils;

import java.util.HashMap;
import java.util.List;

import com.fido.domain.HuffmanTree;
import com.fido.domain.Node;

public class TreeUtils {

	// ��������������ñ��뼯��
	public static HashMap<Character, String> encodingMap(HuffmanTree tree) {
		HashMap<Character, String> hashMap = new HashMap<Character, String>();
		List<Node> leafNode = tree.getLeafNodeList();// ���Ҷ�ӽ�㼯��
		for (Node leaf : leafNode) {
			String encoding = "";
			Node currentNode = leaf;// ������ǰ��Node
			do {
				if (currentNode.ifLeftNode()) {
					encoding = '0' + encoding;
				} else {
					encoding = '1' + encoding;
				}
				currentNode = currentNode.getParent();
			} while (currentNode.getParent() != null);
			hashMap.put(leaf.getData(), encoding);
		}
		return hashMap;
	}
	//�����ַ������ù�����������
    public static String encoding(String decoding,HashMap<Character,String> hashMap){
    	StringBuffer buffer=new StringBuffer();
    	for(int i=0;i<decoding.length();i++){
    		 char c=decoding.charAt(i);
    		 buffer.append(hashMap.get(c));		 
    	} 	
		return buffer.toString();
    	
    }
	// ����������
	public static String decoding(String encoding, HuffmanTree tree) {
		StringBuffer decoding = new StringBuffer();
		for (int i = 0; i < encoding.length(); i++) {
			Node currentNode = tree.getRoot();// ��øù��������ĸ����
			do {
				char c = encoding.charAt(i);
				System.out.print(c + " ");
				if (c == '0') {
					currentNode = currentNode.getLeftChild();
				} else {
					currentNode = currentNode.getRightChile();
				}
				i++; // Ҫ��δ�ҵ�Ҷ�ӽ��֮ǰ��ֹͣ���ַ�
			} while (!currentNode.ifLeafNode());
			i--; // ��Ϊǰ�������һ��i������Ҫ����ȥ����Ȼ��������һ���ַ�
			decoding = decoding.append(currentNode.getData());
		}
		return decoding.toString();
	}
	

}
