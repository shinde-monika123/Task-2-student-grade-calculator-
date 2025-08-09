import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentGradeCalculatorGUI extends JFrame {
    private JTextField[] markFields;
    private JLabel resultLabel;
    private JButton calculateButton;

    public StudentGradeCalculatorGUI(int subjectCount) {
        setTitle("Student Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        markFields = new JTextField[subjectCount];
        JPanel inputPanel = new JPanel(new GridLayout(subjectCount, 2, 5, 5));

        for (int i = 0; i < subjectCount; i++) {
            inputPanel.add(new JLabel("Subject " + (i + 1) + " Marks:"));
            markFields[i] = new JTextField();
            inputPanel.add(markFields[i]);
        }

        resultLabel = new JLabel(" ");
        calculateButton = new JButton("Calculate");

        calculateButton.addActionListener(e -> calculateGrade());

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.add(calculateButton);
        bottomPanel.add(resultLabel);

        add(inputPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void calculateGrade() {
        int total = 0;
        int count = markFields.length;

        try {
            for (JTextField field : markFields) {
                int mark = Integer.parseInt(field.getText());
                if (mark < 0 || mark > 100) {
                    throw new NumberFormatException("Invalid mark: " + mark);
                }
                total += mark;
            }

            double average = (double) total / count;
            String grade;

            if (average >= 90) grade = "A";
            else if (average >= 75) grade = "B";
            else if (average >= 60) grade = "C";
            else if (average >= 40) grade = "D";
            else grade = "F";

            resultLabel.setText("<html>Total: " + total + "<br>Average: " + average + "%<br>Grade: " + grade + "</html>");

        } catch (NumberFormatException e) {
            resultLabel.setText("❌ Please enter valid marks (0-100)");
        }
    }

    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Enter number of subjects:");
        try {
            int subjectCount = Integer.parseInt(input);
            if (subjectCount <= 0) throw new NumberFormatException();

            SwingUtilities.invokeLater(() -> {
                StudentGradeCalculatorGUI app = new StudentGradeCalculatorGUI(subjectCount);
                app.setVisible(true);
            });

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number of subjects.");
        }
    }
}