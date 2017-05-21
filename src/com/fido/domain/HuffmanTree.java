package com.fido.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fido.utils.FidoUtils;

/**
 * 
 * @author: fido
 * @date:2017年5月12日 下午10:59:30
 * @description：构建哈夫曼树
 *
 */
public class HuffmanTree implements Serializable{
	private Node root;// 哈夫曼树的根结点
	private List<Node> leafNodeList;//哈夫曼树的叶子结点集合

	public HuffmanTree(List<Node> list) {
		RootAndLeaf rootandleaf=this.createHuffmanTree(list);
		this.root=rootandleaf.getRoot();
		this.leafNodeList=rootandleaf.getLeafNodeList();
	}

	// 构建哈夫曼树并返回根结点和叶子结点集合
	public RootAndLeaf createHuffmanTree(List<Node> list) {
		List<Node> leafList=new ArrayList<Node>();
		while (list.size() > 1) {
			int parentWeight = list.get(0).getWeight()
					+ list.get(1).getWeight();
			Node node = new Node('\0', parentWeight);
			node.setLeftChild(list.get(0));
			node.setRightChile(list.get(1));
			list.get(0).setParent(node);
			list.get(1).setParent(node);
			//判断此时权值最小的两个是否为叶子结点
			if(list.get(0).ifLeafNode()){
				leafList.add(list.get(0));
			}
			if(list.get(1).ifLeafNode()){
				leafList.add(list.get(1));
			}
			//删除掉最小的两个，并把最新的放进去
			list.remove(0);
			list.remove(0);
			list.add(node);
			FidoUtils.quickSort(list, 0, list.size()-1);
		}
		return new RootAndLeaf(list.get(0),leafList);
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
	//前序遍历
	public void preOrder(Node root){
		if(root!=null){
			System.out.println(root.toString());
			preOrder(root.getLeftChild());
			preOrder(root.getRightChile());
		} 
	}

	public List<Node> getLeafNodeList() {
		return leafNodeList;
	}

	public void setLeafNodeList(List<Node> leafNodeList) {
		this.leafNodeList = leafNodeList;
	}
	
   
}
