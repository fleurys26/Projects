import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.plaf.DimensionUIResource;


public class Server extends JFrame{

    Listener ecoute ;
    JPanel pan1 = new JPanel();
    JPanel pan2 = new JPanel();
    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();
    JButton bouton = new JButton();
    JTextField field = new JTextField("");
    JTextArea chatArea = new JTextArea();
    Socket socket;
    ServerSocket serverSocket;
    ObjectOutputStream dataOut;
    ObjectInputStream dataIn;
    BufferedReader pen ;
    String message="";
    int totalClients = 100;
    int port = 6789;

    public Server()
    {
        initComponents();
        this.setContentPane(pan1);
        this.setVisible(true);
        label1.setVisible(true);
        
    }
    public void startRunning()
{
    try
    {
        serverSocket=new ServerSocket(port, totalClients);
        
            try
            {
                label1.setText(" Waiting for a client...");
                socket=serverSocket.accept();
                label1.setText(" Now Connected to "+socket.getInetAddress().getHostName());
                dataOut = new ObjectOutputStream(socket.getOutputStream());
                dataIn = new ObjectInputStream(socket.getInputStream());
            while (true) {
                whileChatting();
            }


            }catch(EOFException eofException)
            {
            }
        
    }
    catch(IOException ioException)
    {
            ioException.printStackTrace();
    }
}
public void whileChatting() throws IOException{

    String message="";    
    field.setEditable(true);
     
    do{

        try{

            message = (String) dataIn.readObject();
            chatArea.append("\n"+message);
        }
        catch(Exception e){

        }
    } while(!message.equals("Client - END"));

}
    public void initComponents()
    {

        field.setPreferredSize(new DimensionUIResource(300, 40));
        field.setBounds(100, 100, 270, 30);
        bouton.setBounds(310, 50, 80, 30);
        bouton.setText("send");
        bouton.addActionListener(new Listener(this));
        chatArea.setColumns(30);
        chatArea.setRows(10);
        pan1.add(field);
        pan1.add(bouton);
        label1.setForeground(new java.awt.Color(53, 128, 187));
        label1.setText("Write your text here");
        label1.setBounds(30, 30, 150, 20);
        pan1.add(label1);
        pan1.add(chatArea);
        this.setTitle("SERVER");            
        this.setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

    field.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            sendMessage(field.getText());
            field.setText("");
        }
    });  
}
public void sendMessage(String message)
{
    
    try
    {
        //System.out.println("cc"+ dataOut);
        dataOut.writeObject("Server - " + message);
        dataOut.flush();
        chatArea.append("\nServer - "+message);
        
    }
    catch(Exception e)
    {
        System.out.println(e);
        chatArea.append("\n Unable to Send Message");
    }

}    
}





