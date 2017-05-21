package com.fido.domain;

import java.util.List;

public class RootAndLeaf {
     private Node root;
     private List<Node> leafNodeList;
	public RootAndLeaf(Node root, List<Node> leafNodeList) {
		this.root=root;
		this.leafNodeList=leafNodeList;
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
