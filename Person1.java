
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

import java.net.*;

import javax.swing.*;



public class Person1 extends JFrame {


    ServerSocket server;
    Socket socket;

    BufferedReader br;
    PrintWriter out;


    private JLabel heading =new JLabel("Person1 Area");
    private JTextArea messageArea = new JTextArea();
    private JTextField messageInput=new JTextField();
    private Font font=new Font("Roboto",Font.PLAIN,20);


    public Person1()
    { try 

        {

        server =new ServerSocket(5000);
        System.out.println("Person1 is Ready to accept Connection");
        System.out.println("Waiting.....");

       socket= server.accept();

       br=new BufferedReader(new InputStreamReader(socket.
       getInputStream()));

        out =new PrintWriter(socket.getOutputStream());

    
        createGUI();
       hangleEvents();

        startReading();
       // startWriting ();

       

        }
       catch (Exception e)
       {
        System.out.println(e);
       }
        
       }





 /**
 * 
 */
void hangleEvents()
 {
     messageInput.addKeyListener(new KeyListener(){

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void keyReleased(KeyEvent e) {

            // TODO Auto-generated method stub

            System.out.println("key released"+e.getKeyCode());
            if(e.getKeyCode()==10){

               // System.out.println("You are Pressed enter");
               String contentToSend=messageInput.getText();
               messageArea.append(("Me :"+contentToSend+"\n"));

               out.println(contentToSend);
               out.flush();

               messageInput.setText("");
               messageInput.requestFocus();
        


            }
            
         }

     });


    }


 void createGUI()

 {

    
    this.setTitle("Person1 Messager[END]");
    this.setSize(500,400);
    this.setLocationRelativeTo(null);   // set for middle postion
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     //coding for componet

     heading.setFont(font);
     messageArea.setFont(font);
     messageInput.setFont(font);
    // heading.setIcon(new ImageIcon("plo.jpg"));
     heading.setHorizontalTextPosition(SwingConstants.CENTER);
     heading.setVerticalTextPosition(SwingConstants.BOTTOM);
     
     heading.setHorizontalAlignment(SwingConstants.CENTER);
     heading.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

     //message input
     messageArea.setEditable(false);
     messageInput.setHorizontalAlignment(SwingConstants.CENTER);

     // Frame layout
     this.setLayout(new BorderLayout());

     // adding the componets to freame
     this.add(heading,BorderLayout.NORTH);

     JScrollPane jScrollPane=new JScrollPane(messageArea);
     this.add(jScrollPane,BorderLayout.CENTER);
     this.add(messageInput,BorderLayout.SOUTH);


    this.setVisible(true);


    }





public void startReading()
 {
    // thread-read 

    Runnable r1 =()->{

        System.out.println("Reader is Started");
        try {

        while(true)
        {
            
         

             String   msg = br.readLine();
        

            if(msg.equals("exit"))

            {
                System.out.println("Person2 Terminated the chat");

                JOptionPane.showMessageDialog(this,"Person1 Terminated the chat");
                 messageInput.setEnabled(false);

                socket.close();
                break;

            }

          // System.out.println("Cilent : "+msg);
          messageArea.append("Person2 :"+ msg+"\n");
        

               
        }
            }

            catch (IOException e)
            {
                System.out.println("Connection is close");
               // e.printStackTrace();
            }

        

    };
    new Thread(r1).start();

 }    /* */


 
    public static void main (String[]a)
    {

       System.out.println("The Cummunication is....going on ");
            
       new Person1();

    }
    
}
