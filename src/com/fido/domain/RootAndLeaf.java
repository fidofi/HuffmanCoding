package com.fido.domain;

import java.util.List;

public class RootAndLeaf {
	private Node root;// 标明根结点
	private List<Node> leafNodeList;// 叶子结点集合

	public RootAndLeaf(Node root, List<Node> leafNodeList) {
		this.root = root;
		this.leafNodeList = leafNodeList;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public List<Node> getLeafNodeList() {
		return leafNodeList;
	}

	public void setLeafNodeList(List<Node> leafNodeList) {
		this.leafNodeList = leafNodeList;
	}

}
