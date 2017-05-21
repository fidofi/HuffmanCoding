package com.fido.ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;

import com.fido.dao.HuffmanDao;
import java.awt.Color;

public class MainFrame {

	private JFrame frame;
	private HuffmanDao dao=new HuffmanDao();
	private JPanel panel;

	 //主程序入口点
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			MainFrame window = new MainFrame();
			window.frame.setVisible(true);
			window.frame.setResizable(false);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MainFrame() {
		initialize();
	}
	
	//放组件
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 333);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel=new JPanel(){
			protected void paintComponent(Graphics g) {  
                ImageIcon icon = new ImageIcon("src/1.jpg");  
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, icon.getIconWidth(),  
                        icon.getIconHeight(), icon.getImageObserver());  
  
            }  
  
        };  
		panel.setLayout(null);
		frame.setContentPane(panel);
		

		
		
	  
		
		
		JButton encodingButton = new JButton("编码");//编码按钮
		encodingButton.setBackground(Color.GRAY);
		encodingButton.setForeground(Color.BLACK);
		encodingButton.setFont(new Font("方正稚艺简体", Font.PLAIN, 26));
		encodingButton.setBounds(157, 107, 110, 43);
		panel.add(encodingButton);
		encodingButton.addActionListener(new ActionListener(){ //压缩按钮的事件监听器
			@Override
			public void actionPerformed(ActionEvent e) {
				String path=showChooser();
				if(path!=null){
					String newPath=path.substring(0, path.lastIndexOf("\\")+1);
					newPath=newPath+path.substring(path.lastIndexOf("\\")+1, path.lastIndexOf("."))+".fido";
					System.out.println(newPath);
					try {
						dao.encoding(path, newPath);
						JOptionPane.showMessageDialog(null, "压缩成功");
					} catch (IOException e1) {
						e1.printStackTrace();
						System.out.println("压缩时候出错了");
					}
					
				}
				
			}
			
		});
		
		JButton decodingButton = new JButton("解码");//解码按钮
		decodingButton.setBackground(Color.GRAY);
		decodingButton.setFont(new Font("方正稚艺简体", Font.PLAIN, 26));
		decodingButton.setBounds(154, 195, 113, 43);
		panel.add(decodingButton);
		decodingButton.addActionListener(new ActionListener(){  //解压按钮的事件监听器
			@Override
			public void actionPerformed(ActionEvent e) {
				String path=showChooser();
                if(path!=null){
    				String newPath=path.substring(0, path.lastIndexOf("\\")+1);
    				newPath=newPath+path.substring(path.lastIndexOf("\\")+1, path.lastIndexOf("."))+".txt";
    				System.out.println(newPath);
    			    try {
    					dao.decoding(path, newPath);
    					JOptionPane.showMessageDialog(null, "解压成功");
    				} catch (ClassNotFoundException e1) {
    					e1.printStackTrace();
    					System.out.println("解压的时候文件未找到!");
    				} catch (IOException e1) {
    					e1.printStackTrace();
    					System.out.println("解压的时候出错了");
    				}
                } 		
				
			}
			
			
		});

		
		JLabel titleLabel = new JLabel("哈夫曼编码解码工具"); //标题
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("方正稚艺简体", Font.PLAIN, 35));
		titleLabel.setBounds(57, 21, 328, 66);
		panel.add(titleLabel);
		
		JLabel authorLabel = new JLabel("Copyright@fido");
		authorLabel.setForeground(Color.WHITE);
		authorLabel.setFont(new Font("等线", Font.PLAIN, 16));
		authorLabel.setBounds(157, 266, 113, 18);
		panel.add(authorLabel);
	}
	
	//弹出文件选择框，返回被选择文件的路径
	 public String showChooser(){
		 JFileChooser chooser=new JFileChooser();
		 chooser.setFileFilter(new FileFilter(){  //设置可以使用的文件类型
			public boolean accept(File f) {
				  if(f.getName().endsWith(".txt")||f.isDirectory()||f.getName().endsWith(".fido")){
			          return true;
			        }
			        return false;
			}

			@Override
			public String getDescription() { //需要重写的方法
				// TODO Auto-generated method stub
				return null;
			}
		 });
		 int returnVal = chooser.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	System.out.println( chooser.getSelectedFile().getPath());
		          return  chooser.getSelectedFile().getPath();
	 }
		     return null;
     }

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
