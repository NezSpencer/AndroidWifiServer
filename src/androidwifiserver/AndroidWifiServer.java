/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package androidwifiserver;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nezspencer
 */
public class AndroidWifiServer implements ActionListener {

    private JFrame appFrame;
    private JPanel appPanel;
    public static JTextField textField;
    private JButton startServerButton;
    
    private SocketNode server;
    
    private boolean isServerStarted;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new AndroidWifiServer().setUpScreen();
       
    }
    
    public void setUpScreen(){
    
        Dimension d =Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("width ="+d.width+" hieght="+d.height);
        
        appFrame = new JFrame("Android Wifi Server");
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setSize(400, 400);
        JFrame.setDefaultLookAndFeelDecorated(true);
        appFrame.setResizable(false);
        appFrame.setPreferredSize(new Dimension(400,400));
        
        // create widgets
        
        appPanel = new JPanel();
        appPanel.setLayout(new BoxLayout(appPanel, BoxLayout.Y_AXIS));
       
        startServerButton = new JButton("Start Server");
        startServerButton.setSize(100, 200);
        
        textField = new JTextField(100);
        
        appPanel.add(Box.createRigidArea(new Dimension(150,200)));
        appPanel.add(startServerButton,Component.CENTER_ALIGNMENT);
        appPanel.add(Box.createRigidArea(new Dimension(150,100)));
        appPanel.add(textField,Component.BOTTOM_ALIGNMENT);
        //appFrame.getContentPane().setLayout(new BoxLayout(appFrame.getContentPane(), BoxLayout.PAGE_AXIS));
        appFrame.setContentPane(appPanel);
        
        
        startServerButton.addActionListener(this);
        
        appFrame.setVisible(true);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(!isServerStarted)
        {
            server = new SocketNode();
            server.startServer();
            isServerStarted = true;
            startServerButton.setText("Stop Server");
        }
            
        else
        {
            server.stopServer();
            isServerStarted =false;
            startServerButton.setText("Start Server");
        }
            
    }
    
    
}
