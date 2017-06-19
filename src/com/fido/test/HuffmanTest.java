package com.fido.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import com.fido.dao.HuffmanDao;
import com.fido.domain.HuffmanTree;
import com.fido.domain.Node;
import com.fido.utils.ArticleUtils;
import com.fido.utils.FidoUtils;
import com.fido.utils.TreeUtils;

public class HuffmanTest {
	
	//测试ArticleDao里面的读取并计数方法
	//@Test
    public void testReadAndCount(){
    	HashMap<Character,Integer> hashMap=ArticleUtils.readAndCount("G://测试.txt").getMap();
    	for(Map.Entry<Character,Integer> entry:hashMap.entrySet()){
    		 System.out.println("字符:"+entry.getKey()+"次数:"+entry.getValue());
    	}
    }
    
    //测试工具类里的快排
    //@Test
    public void testQuickSort(){
    	 List<Node> testList=new ArrayList<Node>();
    	 int j=10;
         for(int i=1;i<11;i++){
        	 testList.add(new Node((char) (65+i),new Random().nextInt(10)+1));
         }
         FidoUtils.showList(testList);
          FidoUtils.quickSort(testList,0,testList.size()-1);
          System.out.println("排序后：");
          FidoUtils.showList(testList);
    }
    
    //测试工具类里的划分函数
   // @Test
    public void testPartition(){
    	 List<Node> testList=new ArrayList<Node>();
         for(int i=1;i<11;i++){
        	 testList.add(new Node((char) (65+i),new Random().nextInt(10)+1));
         }
         FidoUtils.showList(testList);
         System.out.println(FidoUtils.partition(testList, 0, testList.size()-1));
         FidoUtils.showList(testList);
    }
    //测试将给定HashMap按照频数放进list里面
    public void testMapAndList(){
    	HashMap<Character,Integer> hashMap=ArticleUtils.readAndCount("G://测试01.txt").getMap();
    	FidoUtils.showList(FidoUtils.getNodeList(hashMap));
    }
    
    //测试构造哈夫曼树
   // @Test
    public void testCreateTree(){
    	HashMap<Character,Integer> hashMap=ArticleUtils.readAndCount("G://测试.txt").getMap();
    	HuffmanTree tree=new HuffmanTree(FidoUtils.getNodeList(hashMap));
    	Node root=tree.getRoot();
    	System.out.println(root.toString());
    	System.out.println(tree.getLeafNodeList().size());
    }
    //测试前序遍历
    //@Test
    public void testPreOrder(){
    	HashMap<Character,Integer> hashMap=ArticleUtils.readAndCount("G://测试.txt").getMap();
    	HuffmanTree tree=new HuffmanTree(FidoUtils.getNodeList(hashMap));
    	tree.preOrder(tree.getRoot());
    }
    //测试获得编码集合
 // @Test
    public void testEncodingMap(){
    	HashMap<Character,Integer> hashMap=ArticleUtils.readAndCount("G://数据结构课程设计/0109.txt").getMap();
    	//System.out.println(ArticleUtils.readAndCount("G://测试.txt"));
    	HuffmanTree tree=new HuffmanTree(FidoUtils.getNodeList(hashMap));
    	FidoUtils.showMap(TreeUtils.encodingMap(tree));
    }
    //测试解码
  //  @Test
    public void testDecoding(){
    	HashMap<Character,Integer> hashMap=ArticleUtils.readAndCount("G://测试.txt").getMap();
    	HuffmanTree tree=new HuffmanTree(FidoUtils.getNodeList(hashMap));
    	System.out.println(TreeUtils.decoding("001011010000", tree));
    }
    //测试给定字符来编码
    //@Test
    public void testEncoding(){
    	HashMap<Character,Integer> hashMap=ArticleUtils.readAndCount("G://测试.txt").getMap();
    	HuffmanTree tree=new HuffmanTree(FidoUtils.getNodeList(hashMap));
    	HashMap<Character,String> encodingMap=TreeUtils.encodingMap(tree);
    	System.out.println(TreeUtils.encoding("你是垃圾辣鸡辣鸡", encodingMap));
    }
    //测试将编码结果写入新文件
    @Test
    public void testFileEncoding() throws IOException{
    	HuffmanDao dao=new HuffmanDao();
    	dao.encoding("G://测试.txt","G://测试编码.fido" );
    }
    //测试将解码结果写入文件
   //@Test
    public void testFileDecoding() throws ClassNotFoundException, IOException{
    	HuffmanDao dao=new HuffmanDao();
    	dao.decoding("G://测试编码.fido","G://测试2.txt" );
    }
}
