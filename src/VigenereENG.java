import java.util.ArrayList;
import java.util.List;


public class VigenereENG {


    private final List<String> history = new ArrayList<>();


    public String encrypt(String plaintext, String key) {
        validateKey(key);
        StringBuilder result = new StringBuilder();
        String cleanKey = key.toUpperCase().replaceAll("[^A-Z]", "");
        int keyIndex = 0;
        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                int shift = cleanKey.charAt(keyIndex % cleanKey.length()) - 'A';
                if (Character.isUpperCase(c)) {
                    result.append((char) ('A' + (c - 'A' + shift) % 26));
                } else {
                    result.append((char) ('a' + (c - 'a' + shift) % 26));
                }
                keyIndex++;
            } else {
                result.append(c);
            }
        }
        String encrypted = result.toString();
        history.add("ENC | Key: " + key + " | " + plaintext + " → " + encrypted);
        return encrypted;
    }


    public String decrypt(String ciphertext, String key) {
        validateKey(key);
        StringBuilder result = new StringBuilder();
        String cleanKey = key.toUpperCase().replaceAll("[^A-Z]", "");
        int keyIndex = 0;
        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                int shift = cleanKey.charAt(keyIndex % cleanKey.length()) - 'A';
                if (Character.isUpperCase(c)) {
                    result.append((char) ('A' + (c - 'A' - shift + 26) % 26));
                } else {
                    result.append((char) ('a' + (c - 'a' - shift + 26) % 26));
                }
                keyIndex++;
            } else {
                result.append(c);
            }
        }
        String decrypted = result.toString();
        history.add("DEC | Key: " + key + " | " + ciphertext + " → " + decrypted);
        return decrypted;
    }


    private void validateKey(String key) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Key cannot be empty.");
        }
        if (!key.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Key must contain only letters.");
        }
    }


    public List<String> getHistory() {
        return new ArrayList<>(history);
    }


    public void clearHistory() {
        history.clear();
    }
}
