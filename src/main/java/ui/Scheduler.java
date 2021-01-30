package ui;

import schedulers.*;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;

public class Scheduler extends JFrame {

    private JPanel mainPanel;
    private JComboBox<String> dropDown;
    private JProgressBar progressBar1;
    private JList<String> taskList;
    private DefaultListModel<String> displayTasks;
    private JTextField taskName;
    private JTextField RemTime;
    private JTextField relDeadline;
    private JButton saveNewButton;
    private JButton tickButton;
    private JButton saveExistingButton;
    private JButton switchButton;
    private JTextField currentField;
    private JButton clearButton;
    private JButton delButton;


    private AbstractScheduler scheduler;
    private ArrayList<Task> task;
    private Task currentTask;
    private int ticks;
    private boolean switched;


    public Scheduler() {
        super("Take control of Your Time!");
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        ticks = 0;
        scheduler = new SRPTScheduler(ticks);
        displayTasks = new DefaultListModel<>();
        taskList.setModel(displayTasks);
        saveExistingButton.setEnabled(false);
        switchButton.setEnabled(false);
        delButton.setEnabled(false);

        saveNewButton.addActionListener(e -> saveNewTask());
        saveExistingButton.addActionListener(e -> saveExistingTask());
        taskList.addListSelectionListener(e -> selectTask());
        clearButton.addActionListener(e -> clearForm());
        delButton.addActionListener(e -> deleteTask());
        dropDown.addActionListener(e -> switchScheduler());
        tickButton.addActionListener(e -> tictok());
        switchButton.addActionListener(e -> switchTask());
    }

    public void switchTask() {
        switched = true;
        int taskNumber = taskList.getSelectedIndex();
        if (taskNumber >= 0) {
            currentField.setText(task.get(taskNumber).getTaskName());
            currentTask = task.get(taskNumber);
        }
    }

    public void tictok() {
        scheduler.tick(currentTask);
        ticks++;
        refreshList();
    }

    public void switchScheduler() {
        int type = dropDown.getSelectedIndex();
        if (type == 0) {
            scheduler = new SRPTScheduler(ticks);
        } else if (type == 1) {
            scheduler = new EDFScheduler(ticks);
        } else {
            scheduler = new DMScheduler(ticks);
        }
        for (Task t : task) {
            scheduler.addTask(t);
        }
        refreshList();
    }

    public void clearForm() {
        RemTime.setText("");
        relDeadline.setText("");
        taskName.setText("");
        taskList.clearSelection();
    }

    public void selectTask() {
        int taskNumber = taskList.getSelectedIndex();
        if (taskNumber >= 0) {
            Task t = task.get(taskNumber);
            taskName.setText(t.getTaskName());
            RemTime.setText(String.valueOf(t.getRemProcTime()));
            relDeadline.setText(String.valueOf(t.getRelDeadline()));
            saveExistingButton.setEnabled(true);
            delButton.setEnabled(true);
            if (task.size() >= 2) {
                switchButton.setEnabled(true);
            }
        } else {
            saveExistingButton.setEnabled(false);
            switchButton.setEnabled(false);
            delButton.setEnabled(false);
        }
    }

    public void deleteTask() {
        int taskNumber = taskList.getSelectedIndex();
        if (taskNumber >= 0) {
            delTask(task.get(taskNumber));
        }
    }

    public void saveNewTask() {
        Task tn = new Task(Integer.parseInt(RemTime.getText()), Integer.parseInt(relDeadline.getText()), taskName.getText());
        saveTask(tn);
    }

    public void saveExistingTask() {
        int taskNumber = taskList.getSelectedIndex();
        if (taskNumber >= 0) {
            Task t = task.get(taskNumber);
            scheduler.delTask(t);
            task.remove(t);
            Task n = new Task(Integer.parseInt(RemTime.getText()), Integer.parseInt(relDeadline.getText()), taskName.getText());
            saveTask(n);
        }
    }

    public void saveTask(Task task) {
        scheduler.addTask(task);
        refreshList();
    }

    public void delTask(Task task) {
        scheduler.delTask(task);
        refreshList();
    }

    private void refreshList() {
        task = scheduler.getList();
        displayTasks.removeAllElements();
        for (Task t : task) {
            displayTasks.addElement(t.getTaskName());
        }
        if (!switched) {
            currentTask = task.get(0);
        }
        currentField.setText(currentTask.getTaskName());
    }

    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();
        scheduler.setVisible(true);

        Task task1 = new Task(3, 10, "job application");
        Task task2 = new Task(2, 5, "make a new git repo");
        Task task3 = new Task(1, 3, "clean up your room");
        Task task4 = new Task(1, 3, "feed birds");

        scheduler.saveTask(task1);
        scheduler.saveTask(task2);
        scheduler.saveTask(task3);
        scheduler.saveTask(task4);

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1184275));
        panel1.setForeground(new Color(-4473925));
        mainPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-12828863));
        Font label1Font = this.$$$getFont$$$("Georgia", Font.BOLD, 16, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-12828863));
        label1.setText("Scheduler");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Georgia", -1, 14, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("Select the Type of Scheduler ");
        panel1.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dropDown = new JComboBox();
        Font dropDownFont = this.$$$getFont$$$("Georgia", -1, 14, dropDown.getFont());
        if (dropDownFont != null) dropDown.setFont(dropDownFont);
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Shortest Remaining Process Time");
        defaultComboBoxModel1.addElement("Earliest Deadline First");
        defaultComboBoxModel1.addElement("Deadline Monotonic");
        dropDown.setModel(defaultComboBoxModel1);
        panel1.add(dropDown, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Georgia", -1, 14, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setText("Current Task");
        panel1.add(label3, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentField = new JTextField();
        currentField.setBackground(new Color(-3873281));
        currentField.setEditable(false);
        Font currentFieldFont = this.$$$getFont$$$("Georgia", Font.BOLD, 14, currentField.getFont());
        if (currentFieldFont != null) currentField.setFont(currentFieldFont);
        panel1.add(currentField, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$("Georgia", -1, 14, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setText("Current Task Progress ");
        panel2.add(label4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        progressBar1 = new JProgressBar();
        panel2.add(progressBar1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        taskList = new JList();
        taskList.setBackground(new Color(-5186305));
        Font taskListFont = this.$$$getFont$$$("Georgia", -1, 14, taskList.getFont());
        if (taskListFont != null) taskList.setFont(taskListFont);
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        taskList.setModel(defaultListModel1);
        taskList.setSelectionBackground(new Color(-13220097));
        taskList.setSelectionForeground(new Color(-1));
        taskList.setSelectionMode(0);
        panel2.add(taskList, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        tickButton = new JButton();
        Font tickButtonFont = this.$$$getFont$$$("Georgia", Font.BOLD, 14, tickButton.getFont());
        if (tickButtonFont != null) tickButton.setFont(tickButtonFont);
        tickButton.setText("Tick");
        panel2.add(tickButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        switchButton = new JButton();
        Font switchButtonFont = this.$$$getFont$$$("Georgia", Font.BOLD, 14, switchButton.getFont());
        if (switchButtonFont != null) switchButton.setFont(switchButtonFont);
        switchButton.setText("Switch");
        panel2.add(switchButton, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        delButton = new JButton();
        Font delButtonFont = this.$$$getFont$$$("Georgia", Font.BOLD, 14, delButton.getFont());
        if (delButtonFont != null) delButton.setFont(delButtonFont);
        delButton.setText("Delete");
        panel2.add(delButton, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(8, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$("Georgia", Font.BOLD, 14, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setText("Add New Tasks");
        panel3.add(label5, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$("Georgia", -1, 14, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setText("Task");
        panel3.add(label6, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        taskName = new JTextField();
        Font taskNameFont = this.$$$getFont$$$("Georgia", -1, 14, taskName.getFont());
        if (taskNameFont != null) taskName.setFont(taskNameFont);
        panel3.add(taskName, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label7 = new JLabel();
        Font label7Font = this.$$$getFont$$$("Georgia", -1, 14, label7.getFont());
        if (label7Font != null) label7.setFont(label7Font);
        label7.setText("Estimated Completion Time (hours) ");
        panel3.add(label7, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        RemTime = new JTextField();
        Font RemTimeFont = this.$$$getFont$$$("Georgia", -1, 14, RemTime.getFont());
        if (RemTimeFont != null) RemTime.setFont(RemTimeFont);
        panel3.add(RemTime, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label8 = new JLabel();
        Font label8Font = this.$$$getFont$$$("Georgia", -1, 14, label8.getFont());
        if (label8Font != null) label8.setFont(label8Font);
        label8.setText("Time Till Task is Due (hours)");
        panel3.add(label8, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        relDeadline = new JTextField();
        Font relDeadlineFont = this.$$$getFont$$$("Georgia", -1, 14, relDeadline.getFont());
        if (relDeadlineFont != null) relDeadline.setFont(relDeadlineFont);
        panel3.add(relDeadline, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        saveNewButton = new JButton();
        Font saveNewButtonFont = this.$$$getFont$$$("Georgia", Font.BOLD, 14, saveNewButton.getFont());
        if (saveNewButtonFont != null) saveNewButton.setFont(saveNewButtonFont);
        saveNewButton.setText("Save New");
        panel3.add(saveNewButton, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveExistingButton = new JButton();
        Font saveExistingButtonFont = this.$$$getFont$$$("Georgia", Font.BOLD, 14, saveExistingButton.getFont());
        if (saveExistingButtonFont != null) saveExistingButton.setFont(saveExistingButtonFont);
        saveExistingButton.setText("Save Existing");
        panel3.add(saveExistingButton, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        clearButton = new JButton();
        Font clearButtonFont = this.$$$getFont$$$("Georgia", Font.BOLD, 14, clearButton.getFont());
        if (clearButtonFont != null) clearButton.setFont(clearButtonFont);
        clearButton.setText("Clear Form");
        panel3.add(clearButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
