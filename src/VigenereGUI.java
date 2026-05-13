import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class VigenereGUI extends JFrame {


    private JTextArea inputArea;
    private JTextArea outputArea;
    private JTextField keyField;
    private JButton encryptButton;
    private JButton decryptButton;
    private JButton clearButton;
    private JButton historyClearButton;
    private JTextArea historyArea;


    public VigenereGUI() {
        setTitle("Vigenere Cipher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));


        add(buildTopBar(),  BorderLayout.NORTH);
        add(buildCenter(),  BorderLayout.CENTER);
        add(buildHistory(), BorderLayout.SOUTH);


        pack();
        setMinimumSize(new Dimension(640, 520));
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private JPanel buildTopBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        bar.add(new JLabel("Key:"));
        keyField = new JTextField(16);
        keyField.setFont(new Font("Monospaced", Font.PLAIN, 14));
        bar.add(keyField);
        encryptButton = new JButton("Encrypt");
        decryptButton = new JButton("Decrypt");
        clearButton   = new JButton("Clear");
        bar.add(encryptButton);
        bar.add(decryptButton);
        bar.add(clearButton);
        return bar;
    }


    private JPanel buildCenter() {
        JPanel center = new JPanel(new GridLayout(1, 2, 10, 0));
        inputArea  = buildTextArea();
        outputArea = buildTextArea();
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(245, 245, 245));
        center.add(labeledPanel("Input",  new JScrollPane(inputArea)));
        center.add(labeledPanel("Output", buildOutput()));
        return center;
    }


    private JPanel buildOutput() {
        JPanel panel = new JPanel(new BorderLayout(0, 4));


        panel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        return panel;
    }


    private JPanel buildHistory() {
        JPanel panel = new JPanel(new BorderLayout(0, 4));
        panel.setBorder(BorderFactory.createTitledBorder("History"));
        panel.setPreferredSize(new Dimension(0, 140));
        historyArea = new JTextArea("No operations yet.");
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        historyArea.setLineWrap(true);
        historyArea.setWrapStyleWord(true);
        historyClearButton = new JButton("Clear History");
        historyClearButton.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(new JScrollPane(historyArea), BorderLayout.CENTER);
        panel.add(historyClearButton,           BorderLayout.EAST);
        return panel;
    }


    private JPanel labeledPanel(String title, JComponent content) {
        JPanel panel = new JPanel(new BorderLayout(0, 4));
        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        panel.add(label,   BorderLayout.NORTH);
        panel.add(content, BorderLayout.CENTER);
        return panel;
    }


    private JTextArea buildTextArea() {
        JTextArea area = new JTextArea();
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setRows(8);
        return area;
    }


    public void setEncryptListener(ActionListener l)      { encryptButton.addActionListener(l); }
    public void setDecryptListener(ActionListener l)      { decryptButton.addActionListener(l); }
    public void setClearListener(ActionListener l)        { clearButton.addActionListener(l); }
    public void setHistoryClearListener(ActionListener l) { historyClearButton.addActionListener(l); }


    public String getInputText()  { return inputArea.getText(); }
    public String getOutputText() { return outputArea.getText(); }
    public String getKey()        { return keyField.getText().trim(); }


    public void setOutputText(String text) { outputArea.setText(text); }
    public void setHistoryText(String text) { historyArea.setText(text); }


    public void clearFields() {
        inputArea.setText("");
        outputArea.setText("");
        keyField.setText("");
    }


    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
