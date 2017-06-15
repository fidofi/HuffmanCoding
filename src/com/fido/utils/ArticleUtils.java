package com.fido.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.fido.domain.MapAndData;
/**
 * 
 * @author: fido
 * @date:2017��5��14�� ����8:35:14
 * @description�������ļ���صĹ�����
 *
 */
public class ArticleUtils {
	   //ͳ�������и��ַ������Ӧ�ĸ���
    public static MapAndData readAndCount(String address){
    	HashMap<Character,Integer> hashMap=new HashMap<Character,Integer>();//�½�HashMap
    	StringBuffer buffer=new StringBuffer();
    	 File file=new File(address);
    	 FileReader fileStream=null;
    	 BufferedReader in=null;
    	 if(file.exists()){ //�жϸ��ļ��Ƿ����
    		 try {
				fileStream=new FileReader(file);
				in=new BufferedReader(fileStream); //��ö�ȡ���ļ��Ļ�����
				int aa;//��ʾ��ȡ�����ַ�
				char[]c=new char[1024];
				while((aa=in.read(c,0,c.length))!=-1){//δ���ļ�ĩβ
					for(int i=0;i<c.length;i++){
						char word=c[i];
						if(word==0){ //���ַ���
							break;
						}
						 if(!hashMap.containsKey(word)){ //�������ַ���һ�γ���
							 
							 hashMap.put(word,1);
						 }
						 //��������ַ��Ѿ����ֹ�һ����
						 else{ 
							int value=hashMap.get(word);
							hashMap.put(word, ++value);//������1�ٷŻ�ȥ
						 }
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		 //���йر�������
    		 finally{ 
    			 if(in!=null){
    				 try {
						in.close();
					} catch (IOException e) {
						System.out.println("�������ر�������!");
						e.printStackTrace();
					}
    			 }
    		 }
    	 }
    	 else{
    	    System.out.println("�ļ�δ�ҵ�!");
    	 }
    	 MapAndData md=new MapAndData(hashMap);
    	 return md;
    }
    /**
     * 
     * @datae��2017��5��14��
     * @author:by fido       
     * @param tad  
     * @return �������Ĺ������������������ı�д���ļ�
     * @throws IOException 
     */
//    public static void writeObject(MapAndBinary mb,String newPath) throws IOException{
//    	File file=new File(newPath);
//    	FileOutputStream fileOut=null;
//    	BufferedOutputStream out=null;
//    	//ObjectOutputStream out=null;
//          try{
//      		fileOut=new FileOutputStream(file);
//      		out=new BufferedOutputStream(fileOut);
//      		if(mb!=null){
//      		int[] data=mb.getData();
//               for(int i=0;i<data.length;i++){
//            	        out.write(data[i]);
//               }
//               out.flush();
//          }
//          }
//    		finally{
//    			out.close();
//    		}
//    	   
//    }
}
