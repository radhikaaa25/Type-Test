// Utils.java
public class Utils {
    public static int calculateWPM(String text, int timeInSeconds) {
        int words = text.trim().split("\\s+").length;
        return (words * 60) / timeInSeconds;
    }

    public static int calculateErrors(String originalText, String typedText) {
        int errors = 0;
        String[] originalWords = originalText.split("\\s+");
        String[] typedWords = typedText.split("\\s+");

        int length = Math.min(originalWords.length, typedWords.length);
        for (int i = 0; i < length; i++) {
            if (!originalWords[i].equals(typedWords[i])) {
                errors++;
            }
        }
        return errors + Math.abs(originalWords.length - typedWords.length);
    }
    public static int countWords(String text) {
        // Trim the text to avoid counting leading/trailing spaces
        text = text.trim();
        // Return 0 if the string is empty
        if (text.isEmpty()) {
            return 0;
        }
        // Split the text by whitespace and count the resulting array's length
        String[] words = text.split("\\s+");
        return words.length;
    }
    public static String getRandomText() {
        String[] texts = {
                "Pink ponies and purple giraffes roamed the field. Cotton candy grew from the ground as a chocolate river meandered off to the side.",
                "The chair sat in the corner where it had been for over 25 years. The only difference was there was someone actually sitting in it.",
                "Sometimes that's just the way it has to be. Sure, there were probably other options, but he didn't let them enter his mind.",
                "What have you noticed today? I noticed that if you outline the eyes, nose, and mouth on your face with your finger, you make an \"I\" which makes perfect sense, but is something I never noticed before.",
                "She reached her goal, exhausted. Even more chilling to her was that the euphoria that she thought she'd feel upon reaching it wasn't there."
        };
        return texts[(int)(Math.random() * texts.length)];
    }
}
