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

    public static String getRandomText() {
        String[] texts = {
                "Practice makes perfect.",
                "Typing fast is a valuable skill.",
                "Consistency is key to improvement.",
                "Focus on accuracy, then build speed.",
                "Take breaks to avoid burnout."
        };
        return texts[(int)(Math.random() * texts.length)];
    }
}
