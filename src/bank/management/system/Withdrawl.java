package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener {

    String pin;
    TextField textField;

    JButton b1, b2;
    Withdrawl(String pin){
        this.pin=pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label1 = new JLabel("MAXIMUM WITHDRAWAL IS RS.10,000");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(460,180,700,35);
        l3.add(label1);

        JLabel label2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(460,220,400,35);
        l3.add(label2);


        textField = new TextField();
        textField.setBackground(new Color(65,125,128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460,260,320,25);
        textField.setFont(new Font("Raleway", Font.BOLD,22));
        l3.add(textField);

        b1 = new JButton("WITHDRAW");
        b1.setBounds(700,362,150,35);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(700,406,150,35);
        b2.setBackground(new Color(65,125,128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);

        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                String amountText = textField.getText().trim();
                Date date = new Date();

                if (amountText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to withdraw");
                    return;
                }

                // Validate amount
                double amount;
                try {
                    amount = Double.parseDouble(amountText);
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid positive amount");
                        return;
                    }
                    if (amount > 10000) { // Max withdrawal limit
                        JOptionPane.showMessageDialog(null, "Withdrawal amount cannot exceed Rs. 10,000");
                        return;
                    }
                    if (amount % 100 != 0) { // Must be in multiples of 100
                        JOptionPane.showMessageDialog(null, "Please enter amount in multiples of Rs. 100");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid numeric amount");
                    return;
                }

                Connn c = new Connn();
                try {
                    // Check balance
                    String balanceQuery = "SELECT COALESCE(SUM(CASE WHEN type = 'Deposit' THEN amount ELSE -amount END), 0) as balance FROM bank WHERE pin = ?";
                    PreparedStatement balanceStmt = c.prepareStatement(balanceQuery);
                    balanceStmt.setString(1, pin);
                    ResultSet rs = balanceStmt.executeQuery();

                    double balance = 0;
                    if (rs.next()) {
                        balance = rs.getDouble("balance");
                    }

                    if (balance < amount) {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                        return;
                    }

                    // Perform withdrawal
                    String insertQuery = "INSERT INTO bank (pin, date, type, amount) VALUES (?, ?, 'Withdrawl', ?)";
                    PreparedStatement insertStmt = c.prepareStatement(insertQuery);
                    insertStmt.setString(1, pin);
                    insertStmt.setDate(2, new java.sql.Date(date.getTime()));
                    insertStmt.setDouble(3, amount);
                    insertStmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");
                    setVisible(false);
                    new main_Class(pin);

                } finally {
                    c.close();
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "An error occurred during withdrawal. Please try again.");
                ex.printStackTrace();
            }
        } else if (e.getSource() == b2) {
            setVisible(false);
            new main_Class(pin);
        }
    }
        }
    }

    public static void main(String[] args) {
        new Withdrawl("");
    }
}
