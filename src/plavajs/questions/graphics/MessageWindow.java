package plavajs.questions.graphics;

import javax.swing.*;

public class MessageWindow extends JDialog {
    private JPanel contentPane;
    private JLabel messageLabel;

    public MessageWindow(String message) {
        setContentPane(contentPane);
        setTitle("Zpráva");
        setModal(true);
        setBounds(800,400, 0, 0);
        setResizable(false);
        messageLabel.setText(message);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
