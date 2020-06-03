/*
        Author : Andrei Datcu

        Minimal Notepad.
        27-05-2020.
*/
package com.wordproc;

import org.w3c.dom.Document;

import javax.swing.*;
import javax.swing.text.EditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import java.io.*;

public class wordform {

    // Main page components :
    private JPanel mainPanel;
    private JButton textOptions;
    private JButton aboutButton;
    private JButton opensaveButton;
    private JTextPane textPane1;

    public wordform() {
        textOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Creating a frame for the options
                JFrame frame = new JFrame("Text Settings");

                // Creating a menu for fonts :
                String[] fontItems = {"Arial","Arial Black","Avant Garde","Courier","Courier New","Georgia","Helvetica","Impact","Monospace","Times"};
                final JComboBox fontCombo;
                fontCombo = new JComboBox(fontItems);
                fontCombo.setEditable(true);
                fontCombo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String fontItems = (String) fontCombo.getSelectedItem();
                    }
                });

                // Adjusting the font size :
                JSlider fontSize;
                fontSize = new JSlider(10,50);
                fontSize.setOrientation(JSlider.HORIZONTAL);
                fontSize.setMinorTickSpacing(1);
                fontSize.setMajorTickSpacing(5);
                fontSize.setPaintTicks(true);
                fontSize.setPaintLabels(true);

                // Font Style : Bold
                JButton boldButton;
                boldButton = new JButton("Bold");
                boldButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String fontItems = (String) fontCombo.getSelectedItem();
                        int sizeValue = fontSize.getValue();
                        Font boldFont = new Font(fontItems,Font.BOLD,sizeValue);
                        textPane1.setFont(boldFont);
                        System.out.println("Text set to bold!");
                    }
                });

                // Font Style : Italic
                JButton italicButton;
                italicButton = new JButton("Italic");
                italicButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String fontItems = (String) fontCombo.getSelectedItem();
                        int sizeValue = fontSize.getValue();
                        Font italicFont = new Font(fontItems,Font.ITALIC,sizeValue);
                        textPane1.setFont(italicFont);
                        System.out.println("Text set to italic!");
                    }
                });

                // Font Style : Bold & Italic
                JButton boldnitalicButton;
                boldnitalicButton = new JButton("Bold & Italic");
                boldnitalicButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String fontItems = (String) fontCombo.getSelectedItem();
                        int sizeValue = fontSize.getValue();
                        Font boldnitalicFont = new Font(fontItems,Font.BOLD | Font.ITALIC,sizeValue);
                        textPane1.setFont(boldnitalicFont);
                        System.out.println("Text set to bold & italic!");
                    }
                });

                // Font Style : Plain
                JButton plainButton;
                plainButton = new JButton("Plain");
                plainButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String fontItems = (String) fontCombo.getSelectedItem();
                        int sizeValue = fontSize.getValue();
                        Font plainFont = new Font(fontItems,Font.PLAIN,sizeValue);
                        textPane1.setFont(plainFont);
                        System.out.println("Text set to plain!");
                    }
                });

                // Components :
                frame.add(fontSize);
                frame.add(fontCombo);
                frame.add(boldButton);
                frame.add(italicButton);
                frame.add(plainButton);
                frame.add(boldnitalicButton);

                // Frame Preferences :
                frame.setPreferredSize(new Dimension(370,150));
                frame.setLayout(new FlowLayout(FlowLayout.CENTER));
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }});

        // About button
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Minimal Notepad\nAuthor : Andrei Datcu", "About",JOptionPane.PLAIN_MESSAGE);
            }
        });

        // Open and Save Chooser button
        opensaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Creating a frame for the options
                JFrame frame = new JFrame("Open/Save Settings");

                // Public converting(just in case) :
                String textArea = (String) textPane1.getText(); // converting JTextPane to a string
                byte[] bytesArray = textArea.getBytes(); // to save a file,you need to convert it in bytes.

                //Save button :
                JButton saveButton;
                saveButton = new JButton("Save note");

                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // File saving algorithm :
                        JFileChooser choosePath = new JFileChooser();
                        choosePath.setDialogTitle("Choose where to save .txt");
                        choosePath.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                        String path;
                        if(choosePath.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
                            try{
                                path = choosePath.getSelectedFile().getAbsolutePath();
                                System.out.println("Save path : " + path);
                                FileOutputStream fos = null;
                                File file;
                                file = new File(path); // Output path
                                fos = new FileOutputStream(file);

                                if(file.exists()){
                                    file.createNewFile(); //if the file doesn't exist,then it creates a new one.
                                }
                                String textArea = (String) textPane1.getText(); // converting JTextPane to a string
                                byte[] bytesArray = textArea.getBytes(); // converting it in bytes
                                fos.write(bytesArray);
                                fos.flush();
                            }

                            catch (IOException ioe){
                                ioe.printStackTrace();
                            }
                        }
                    }
                });

                //Open button :
                JButton openButton;
                openButton = new JButton("Open note");

                openButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser choosePath = new JFileChooser();
                        choosePath.setDialogTitle("Choose txt file");
                        choosePath.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                        String path;
                        if(choosePath.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                            try {
                                path = choosePath.getSelectedFile().getAbsolutePath();
                                System.out.println("Open path : " + path);
                                File file;
                                file = new File(path); // Input path
                                FileReader fileReader = new FileReader(file); // to read the file
                                BufferedReader bufferedReader = new BufferedReader(fileReader); // buffer
                                String line = null;
                                while ((line = bufferedReader.readLine()) != null) {
                                    textPane1.setText(line); // inputting the buffered characters found by the filereader in the JTextPane
                                }
                            } catch (IOException ioe) {
                                ioe.printStackTrace();
                            }
                        }
                    }
                });

                // Components :
                frame.add(saveButton);
                frame.add(openButton);

                // Frame Preferences :
                frame.setPreferredSize(new Dimension(195,100));
                frame.setLayout(new FlowLayout(FlowLayout.CENTER));
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

    }

    // Main Page :
    public static void main(String[] args) {
        JFrame frame = new JFrame("Minimal Notepad");
        frame.setContentPane(new wordform().mainPanel);
        frame.setPreferredSize(new Dimension(500,500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
