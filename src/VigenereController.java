import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import javax.swing.SwingUtilities;


public class VigenereController {


    private final VigenereGUI gui;
    private final VigenereENG engine;


    public VigenereController() {
        gui    = new VigenereGUI();
        engine = new VigenereENG();


        gui.setEncryptListener(e      -> handleEncrypt());
        gui.setDecryptListener(e      -> handleDecrypt());
        gui.setClearListener(e        -> handleClear());
        gui.setHistoryClearListener(e -> handleHistoryClear());
    }


    private void handleEncrypt() {
        String text = gui.getInputText();
        String key  = gui.getKey();
        if (text.isEmpty()) { gui.showError("Input text cannot be empty."); return; }
        try {
            gui.setOutputText(engine.encrypt(text, key));
            refreshHistory();
        } catch (IllegalArgumentException ex) { gui.showError(ex.getMessage()); }
    }


    private void handleDecrypt() {
        String text = gui.getInputText();
        String key  = gui.getKey();
        if (text.isEmpty()) { gui.showError("Input text cannot be empty."); return; }
        try {
            gui.setOutputText(engine.decrypt(text, key));
            refreshHistory();
        } catch (IllegalArgumentException ex) { gui.showError(ex.getMessage()); }
    }


    private void handleClear() { gui.clearFields(); }


    private void handleCopy() {
        String output = gui.getOutputText();
        if (output.isEmpty()) return;
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(output), null);
    }


    private void handleHistoryClear() {
        engine.clearHistory();
        gui.setHistoryText("No operations yet.");
    }


    private void refreshHistory() {
        List<String> history = engine.getHistory();
        if (history.isEmpty()) { gui.setHistoryText("No operations yet."); return; }
        StringBuilder sb = new StringBuilder();
        for (int i = history.size() - 1; i >= 0; i--) {
            sb.append(history.get(i)).append("\n");
        }
        gui.setHistoryText(sb.toString().trim());
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(VigenereController::new);
    }
}

