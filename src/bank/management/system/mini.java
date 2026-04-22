package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class mini extends JFrame implements ActionListener {
    String pin;
    JButton button;
    mini(String pin){
        this.pin = pin;
        getContentPane().setBackground(new Color(255,204,204));
        setSize(400,600);
        setLocation(20,20);
        setLayout(null);

        JLabel label1 = new JLabel();
        label1.setBounds(20,140,400,200);
        add(label1);

        JLabel label2 = new JLabel("TechCoder A.V");
        label2.setFont(new Font("System", Font.BOLD,15));
        label2.setBounds(150,20,200,20);
        add(label2);

        JLabel label3 = new JLabel();
        label3.setBounds(20,80,300,20);
        add(label3);

        JLabel label4 = new JLabel();
        label4.setBounds(20,400,300,20);
        add(label4);

        try {
            Connn c = new Connn();
            try {
                String query = "SELECT card_number FROM login WHERE pin = ?";
                PreparedStatement pstmt = c.prepareStatement(query);
                pstmt.setString(1, pin);
                ResultSet resultSet = pstmt.executeQuery();
                if (resultSet.next()) {
                    String cardNumber = resultSet.getString("card_number");
                    if (cardNumber.length() >= 16) {
                        label3.setText("Card Number:  " + cardNumber.substring(0, 4) + "XXXXXXXX" + cardNumber.substring(12));
                    } else {
                        label3.setText("Card Number:  " + cardNumber);
                    }
                }
            } finally {
                c.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error retrieving card information.");
            e.printStackTrace();
        }

        try {
            Connn c = new Connn();
            try {
                String query = "SELECT date, type, amount FROM bank WHERE pin = ? ORDER BY date DESC LIMIT 10";
                PreparedStatement pstmt = c.prepareStatement(query);
                pstmt.setString(1, pin);
                ResultSet resultSet = pstmt.executeQuery();

                StringBuilder statement = new StringBuilder("<html>");
                double balance = 0;

                while (resultSet.next()) {
                    String date = resultSet.getString("date");
                    String type = resultSet.getString("type");
                    double amount = resultSet.getDouble("amount");

                    statement.append(date).append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                            .append(type).append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                            .append(String.format("%.2f", amount)).append("<br><br>");

                    if ("Deposit".equals(type)) {
                        balance += amount;
                    } else {
                        balance -= amount;
                    }
                }
                statement.append("</html>");
                label1.setText(statement.toString());
                label4.setText("Your Total Balance is Rs " + String.format("%.2f", balance));
            } finally {
                c.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error retrieving transaction history.");
            e.printStackTrace();
        }

        button = new JButton("Exit");
        button.setBounds(20,500,100,25);
        button.addActionListener(this);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        add(button);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new mini("");
    }
}
