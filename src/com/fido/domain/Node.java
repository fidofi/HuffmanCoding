package com.fido.domain;

import java.io.Serializable;

/**
 * 
 * @author: fido
 * @date:2017年5月12日 下午9:40:15
 * @description：构建Huffman树的结点
 *
 */
public class Node implements Serializable{
	  private char data;//数据
      private Node leftChild;//左孩子
      private Node rightChile;//右孩子
      private Node parent;//父母结点
      private int weight;//该结点的权值
      
      public Node(char data,int weight) {
		this.data=data;
		this.weight=weight;
	}
      @Override
    public String toString() {
    	return "字符:"+this.data+" 权重:"+this.weight; 	
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
	//判断是否为左孩子
	public boolean ifLeftNode(){
	    return parent.getLeftChild()==this;
		
	}
	//判断是否为右孩子
	public boolean ifRightNode(){
		return parent.getRightChile()==this;
	}
	//判断是否为叶子结点
	public boolean ifLeafNode(){
		return (this.getLeftChild()==null&&this.getRightChile()==null);
	}
      
}
