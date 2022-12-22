import java.awt.event.*;

public class ListenerServer implements ActionListener{

         Server s ;
         

    public ListenerServer(Server s) {

        this.s=s;
    }

    public void actionPerformed(ActionEvent e) {
        if(this.s.bouton.getText() == "send"){
            this.s.sendMessage(this.s.field.getText());
            this.s.field.setText("");
        }
    }
        
}