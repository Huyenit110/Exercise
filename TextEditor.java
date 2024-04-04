import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextEditor {
    private List<String> lines;
    private File file;

    public TextEditor(String filePath) {
        this.file = new File(filePath);
        this.lines = new ArrayList<>();
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            lines.stream().forEach(writer::println);
            System.out.println("Nội dung đã được lưu vào tệp tin thành công.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            lines = reader.lines().collect(Collectors.toList());
            System.out.println("Nội dung đã được tải từ tệp tin thành công.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addLine(String line) {
        lines.add(line);
    }

    public void removeLine(int index) {
        if (index >= 0 && index < lines.size()) {
            lines.remove(index);
        } else {
            System.out.println("Chỉ số dòng không hợp lệ.");
        }
    }

    public void displayContent() {
        for (int i = 0; i < lines.size(); i++) {
            System.out.println("Dòng " + (i + 1) + ": " + lines.get(i));
        }
    }

    public void recursiveSearchFiles(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        recursiveSearchFiles(file);
                    } else {
                        System.out.println("Tệp tin: " + file.getAbsolutePath());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor("D:\OOP\BAI 1\STVB.docx");

        textEditor.addLine("Xin chào, Thế giới!");
        textEditor.addLine("Đây là một đoạn văn bản mẫu.");
        textEditor.addLine("Chào mừng bạn đến với trình soạn thảo văn bản.");

        textEditor.saveToFile();

        textEditor.loadFromFile();
        textEditor.displayContent();

        File directory = new File("D:\OOP");
        textEditor.recursiveSearchFiles(directory);
    }
}