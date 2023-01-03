import java.net.*;
import javax.swing.*;
import java.io.*;



import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Person2 extends JFrame{

    
    Socket socket;

    BufferedReader br;
    PrintWriter out;

    //Delcare Commponts
    private JLabel heading =new JLabel("Person2 Area");
    private JTextArea messageArea = new JTextArea();
    private JTextField messageInput=new JTextField();
    private Font font=new Font("Roboto",Font.PLAIN,20);

 public  Person2()
 {
    

    try
    {
        System.out.println("Sending request to Person2");
     
     socket=new Socket("127.0.0.1", 5000); 

       System.out.println("Connection Done.");

       
       br=new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out =new PrintWriter(socket.getOutputStream());

        createGUI();
        hangleEvents();

        startReading();
       // startWriting(); // it is console code 



    }
    catch(Exception e)
    {
        System.out.println("Connection failed");
        //e.printStackTrace();

    }
 }

 /**
 * 
 */
private void hangleEvents() {

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

void createGUI(){

    this.setTitle("Person2 Messager[END]");
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
     heading.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

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
            
           

              String  msg = br.readLine();
        

            if(msg.equals("exit"))  {

                System.out.println("Person2 Terminated the chat");

                JOptionPane.showMessageDialog(this,"Person1 Terminated the chat");

                messageInput.setEnabled(false);

                socket.close();
                break;

            }

          
           messageArea.append("Person1 :"+ msg+"\n");

                }
            }

            catch ( Exception e)
            {
         
              //  e.printStackTrace();

              System.out.println("Connection is close");
            }  

    };
    new Thread(r1).start();

 }


    public static void main(String[] args) {
        
        System.out.print("This is client...");

        new  Person2();
    }
    
}
    

