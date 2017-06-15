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
 * @date:2017年5月14日 下午8:35:14
 * @description：操作文件相关的工具类
 *
 */
public class ArticleUtils {
	   //统计文章中各字符与其对应的个数
    public static MapAndData readAndCount(String address){
    	HashMap<Character,Integer> hashMap=new HashMap<Character,Integer>();//新建HashMap
    	StringBuffer buffer=new StringBuffer();
    	 File file=new File(address);
    	 FileReader fileStream=null;
    	 BufferedReader in=null;
    	 if(file.exists()){ //判断该文件是否存在
    		 try {
				fileStream=new FileReader(file);
				in=new BufferedReader(fileStream); //获得读取该文件的缓冲流
				int aa;//表示读取到的字符
				char[]c=new char[1024];
				while((aa=in.read(c,0,c.length))!=-1){//未到文件末尾
					for(int i=0;i<c.length;i++){
						char word=c[i];
						if(word==0){ //空字符串
							break;
						}
						 if(!hashMap.containsKey(word)){ //如果这个字符第一次出现
							 
							 hashMap.put(word,1);
						 }
						 //表明这个字符已经出现过一次了
						 else{ 
							int value=hashMap.get(word);
							hashMap.put(word, ++value);//次数加1再放回去
						 }
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		 //进行关闭流操作
    		 finally{ 
    			 if(in!=null){
    				 try {
						in.close();
					} catch (IOException e) {
						System.out.println("输入流关闭有问题!");
						e.printStackTrace();
					}
    			 }
    		 }
    	 }
    	 else{
    	    System.out.println("文件未找到!");
    	 }
    	 MapAndData md=new MapAndData(hashMap);
    	 return md;
    }
    /**
     * 
     * @datae：2017年5月14日
     * @author:by fido       
     * @param tad  
     * @return 将编码后的哈夫曼树编码规则和新文本写进文件
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
