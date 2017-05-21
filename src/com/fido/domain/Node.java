package com.fido.domain;

import java.io.Serializable;

/**
 * 
 * @author: fido
 * @date:2017��5��12�� ����9:40:15
 * @description������Huffman���Ľ��
 *
 */
public class Node implements Serializable{
	  private char data;//����
      private Node leftChild;//����
      private Node rightChile;//�Һ���
      private Node parent;//��ĸ���
      private int weight;//�ý���Ȩֵ
      
      public Node(char data,int weight) {
		this.data=data;
		this.weight=weight;
	}
      @Override
    public String toString() {
    	return "�ַ�:"+this.data+" Ȩ��:"+this.weight; 	
    }
	public char getData() {
		return data;
	}
	public void setData(char data) {
		this.data = data;
	}
	public Node getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}
	public Node getRightChile() {
		return rightChile;
	}
	public void setRightChile(Node rightChile) {
		this.rightChile = rightChile;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}  
	//�ж��Ƿ�Ϊ����
	public boolean ifLeftNode(){
	    return parent.getLeftChild()==this;
		
	}
	//�ж��Ƿ�Ϊ�Һ���
	public boolean ifRightNode(){
		return parent.getRightChile()==this;
	}
	//�ж��Ƿ�ΪҶ�ӽ��
	public boolean ifLeafNode(){
		return (this.getLeftChild()==null&&this.getRightChile()==null);
	}
      
}
