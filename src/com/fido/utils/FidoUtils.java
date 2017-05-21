package com.fido.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fido.domain.Node;


public class FidoUtils {
       
   /**
    * 
    * @datae��2017��5��13��
    * @author:by fido       
    * @param list
    * @param i
    * @param j
    * @return  ����һ��List�����±꣬�������±��Ӧ�Ľ��
    * @return List<Node>
    */
	public static List<Node> swap(List<Node>list,int i,int j){
		   Node temp=list.get(i);
		   list.set(i, list.get(j));
		   list.set(j, temp);
		   return list;
	}
	/**
	 * 
	 * @datae��2017��5��13��
	 * @author:by fido       
	 * @param list
	 * @param low
	 * @param high
	 * @return ��ø���list�ĵ�һ��Ԫ��������ú��list�е��±�λ�� 
	 * @return int
	 */
	public static int partition(List<Node> list,int low,int high){
		while(high>low){
			Node temp=list.get(low);
			int key=temp.getWeight();//ȡ�û�׼
			while(high>low&&list.get(high).getWeight()>=key){
				high--;
			}
			list.set(low, list.get(high));
            while(high>low&&list.get(low).getWeight()<key){
            	low++;
            }
            Node temp_two=list.get(low);
            list.set(high, temp_two);
            list.set(low, temp);
		}
		return low;			
	}
	
    /**
     * 
     * @datae��2017��5��13��
     * @author:by fido       
     * @param list
     * @param low
     * @param high  
     * @return �ݹ�ʹ�û��ֺ������п�������
     */
	public static void quickSort(List<Node> list,int low,int high){
        if(low>=high){
        	return;
        }
        int index=FidoUtils.partition(list, low, high);
        FidoUtils.quickSort(list, low, index-1);
        FidoUtils.quickSort(list,index+1, high);
	}
	
	
	/**
	 * 
	 * @datae��2017��5��12��
	 * @author:by fido       
	 * @param hashMap
	 * @return ����HashMap����ַ�Ƶ��,�����ʼList
	 * @return List<Node>
	 */
	public static List<Node> getNodeList(HashMap<Character,Integer> hashMap){
    	ArrayList<Node>list=new ArrayList<Node>();
    	for(Map.Entry<Character, Integer> entry:hashMap.entrySet()){
    		     Node node=new Node(entry.getKey(),entry.getValue());
    		     list.add(node);  		
    	}
             FidoUtils.quickSort(list,0,list.size()-1);
             return list;
	}
	
	/**
	 * 
	 * @datae��2017��5��13��
	 * @author:by fido       
	 * @param list  
	 * @return ����list
	 */
	public static void showList(List<Node> list){
		       for(int i=0;i<list.size();i++){
		    	    System.out.println(list.get(i).toString());
		       }
	}
	
    /**
     * 
     * @datae��2017��5��13��
     * @author:by fido       
     * @param map  
     * @return ����map
     */
	public static void showMap(HashMap<Character,String> map){
    	for(Map.Entry<Character,String> entry:map.entrySet()){
    		 System.out.println("�ַ�:"+entry.getKey()+"����:"+entry.getValue());
    	}
	}
	
	public static String getNewAddress(String parent,String child){
		return parent+"//"+child;
	}
	
}
