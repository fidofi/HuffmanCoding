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
	
	//����ArticleDao����Ķ�ȡ����������
	//@Test
    public void testReadAndCount(){
    	HashMap<Character,Integer> hashMap=ArticleUtils.readAndCount("G://����.txt").getMap();
    	for(Map.Entry<Character,Integer> entry:hashMap.entrySet()){
    		 System.out.println("�ַ�:"+entry.getKey()+"����:"+entry.getValue());
    	}
    }
    
    //���Թ�������Ŀ���
    //@Test
    public void testQuickSort(){
    	 List<Node> testList=new ArrayList<Node>();
    	 int j=10;
         for(int i=1;i<11;i++){
        	 testList.add(new Node((char) (65+i),new Random().nextInt(10)+1));
         }
         FidoUtils.showList(testList);
          FidoUtils.quickSort(testList,0,testList.size()-1);
          System.out.println("�����");
          FidoUtils.showList(testList);
    }
    
    //���Թ�������Ļ��ֺ���
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
    //���Խ�����HashMap����Ƶ���Ž�list����
    public void testMapAndList(){
    	HashMap<Character,Integer> hashMap=ArticleUtils.readAndCount("G://����01.txt").getMap();
    	FidoUtils.showList(FidoUtils.getNodeList(hashMap));
    }
    
    //���Թ����������
   // @Test
    public void testCreateTree(){
    	HashMap<Character,Integer> hashMap=ArticleUtils.readAndCount("G://����.txt").getMap();
    	HuffmanTree tree=new HuffmanTree(FidoUtils.getNodeList(hashMap));
    	Node root=tree.getRoot();
    	System.out.println(root.toString());
    	System.out.println(tree.getLeafNodeList().size());
    }
    //����ǰ�����
    //@Test
    public void testPreOrder(){
    	HashMap<Character,Integer> hashMap=ArticleUtils.readAndCount("G://����.txt").getMap();
    	HuffmanTree tree=new HuffmanTree(FidoUtils.getNodeList(hashMap));
    	tree.preOrder(tree.getRoot());
    }
    //���Ի�ñ��뼯��
 // @Test
    public void testEncodingMap(){
    	HashMap<Character,Integer> hashMap=ArticleUtils.readAndCount("G://���ݽṹ�γ����/0109.txt").getMap();
    	//System.out.println(ArticleUtils.readAndCount("G://����.txt"));
    	HuffmanTree tree=new HuffmanTree(FidoUtils.getNodeList(hashMap));
    	FidoUtils.showMap(TreeUtils.encodingMap(tree));
    }
    //���Խ���
  //  @Test
    public void testDecoding(){
    	HashMap<Character,Integer> hashMap=ArticleUtils.readAndCount("G://����.txt").getMap();
    	HuffmanTree tree=new HuffmanTree(FidoUtils.getNodeList(hashMap));
    	System.out.println(TreeUtils.decoding("001011010000", tree));
    }
    //���Ը����ַ�������
    //@Test
    public void testEncoding(){
    	HashMap<Character,Integer> hashMap=ArticleUtils.readAndCount("G://����.txt").getMap();
    	HuffmanTree tree=new HuffmanTree(FidoUtils.getNodeList(hashMap));
    	HashMap<Character,String> encodingMap=TreeUtils.encodingMap(tree);
    	System.out.println(TreeUtils.encoding("����������������", encodingMap));
    }
    //���Խ�������д�����ļ�
    @Test
    public void testFileEncoding() throws IOException{
    	HuffmanDao dao=new HuffmanDao();
    	dao.encoding("G://����.txt","G://���Ա���.fido" );
    }
    //���Խ�������д���ļ�
   //@Test
    public void testFileDecoding() throws ClassNotFoundException, IOException{
    	HuffmanDao dao=new HuffmanDao();
    	dao.decoding("G://���Ա���.fido","G://����2.txt" );
    }
}
