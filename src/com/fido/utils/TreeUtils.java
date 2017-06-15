package com.fido.utils;

import java.util.HashMap;
import java.util.List;

import com.fido.domain.HuffmanTree;
import com.fido.domain.Node;

public class TreeUtils {

	// 给定哈夫曼树获得编码集合
	public static HashMap<Character, String> encodingMap(HuffmanTree tree) {
		HashMap<Character, String> hashMap = new HashMap<Character, String>();
		List<Node> leafNode = tree.getLeafNodeList();// 获得叶子结点集合
		for (Node leaf : leafNode) {
			String encoding = "";
			Node currentNode = leaf;// 标明当前的Node
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
	//给定字符串，用哈夫曼树编码
    public static String encoding(String decoding,HashMap<Character,String> hashMap){
    	StringBuffer buffer=new StringBuffer();
    	for(int i=0;i<decoding.length();i++){
    		 char c=decoding.charAt(i);
    		 buffer.append(hashMap.get(c));		 
    	} 	
		return buffer.toString();
    	
    }
	// 哈夫曼解码
	public static String decoding(String encoding, HuffmanTree tree) {
		StringBuffer decoding = new StringBuffer();
		for (int i = 0; i < encoding.length(); i++) {
			Node currentNode = tree.getRoot();// 获得该哈夫曼树的根结点
			do {
				char c = encoding.charAt(i);
				System.out.print(c + " ");
				if (c == '0') {
					currentNode = currentNode.getLeftChild();
				} else {
					currentNode = currentNode.getRightChile();
				}
				i++; // 要在未找到叶子结点之前不停止读字符
			} while (!currentNode.ifLeafNode());
			i--; // 因为前面算多了一次i，这里要减回去，不然就跳过了一个字符
			decoding = decoding.append(currentNode.getData());
		}
		return decoding.toString();
	}
	

}
