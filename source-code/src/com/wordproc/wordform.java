/*
        Author : Andrei Datcu
        Minotes.
        Project start date : 27-05-2020.
*/
package com.wordproc;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import java.io.*;
import java.util.Scanner;
import java.util.stream.Collectors;

public class wordform {

    // Main page components :
    private JPanel mainPanel;
    private JButton textOptions;
    private JButton aboutButton;
    private JButton opensaveButton;
    private JTextArea textPane1;

    public wordform() {
        textOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Creating a frame for the options
                JFrame frame = new JFrame("Settings");

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

                // Insert bullet :
                JButton bulletButton;
                bulletButton = new JButton(" • Bullet");
                bulletButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String bullet;
                        bullet = "  • ";
                        textPane1.append(bullet);
                        System.out.println("Inserted bullet!");
                    }
                });

                // Insert Note Taking / Dash
                JButton dashButton;
                dashButton = new JButton(" - Dash (Note Taking)");
                dashButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String dash;
                        dash = "    - ";
                        textPane1.append(dash);
                        System.out.println("Inserted dash!");
                    }
                });

                // Bullets & lists
                JLabel bulletsnlistsLabel;
                bulletsnlistsLabel = new JLabel("Bullets & Lists");

                // Font size
                JLabel fontSizeLabel;
                fontSizeLabel = new JLabel("Size");

                // Font type
                JLabel fontTypeLabel;
                fontTypeLabel = new JLabel("Font");

                // Formatting
                JLabel textFormatLabel;
                textFormatLabel = new JLabel("Format");

                // Bullets & lists Combo Box
                final JComboBox bulletsnlistsBox;
                String[] bulletsnlistsArray = {" • Bullet"," - Dash"," ★ Black Star"," ☆ White Star"};
                bulletsnlistsBox = new JComboBox(bulletsnlistsArray);
                bulletsnlistsBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String bulletsnlistsBoxSelectedItem = (String) bulletsnlistsBox.getSelectedItem();
                        if (bulletsnlistsBoxSelectedItem == " • Bullet"){
                            String bullet;
                            bullet = "\n    • ";
                            textPane1.append(bullet);
                            System.out.println("Inserted bullet!");
                        }
                        if (bulletsnlistsBoxSelectedItem == " - Dash"){
                            String dash;
                            dash = "\n    - ";
                            textPane1.append(dash);
                            System.out.println("Inserted dash!");
                        }
                        if (bulletsnlistsBoxSelectedItem == " ★ Black Star"){
                            String blackStar;
                            blackStar = "\n    ★ ";
                            textPane1.append(blackStar);
                            System.out.println("Inserted black star!");
                        }
                        if (bulletsnlistsBoxSelectedItem == " ☆ White Star"){
                            String blackStar;
                            blackStar = "\n    ☆ ";
                            textPane1.append(blackStar);
                            System.out.println("Inserted white star!");
                        }
                    }
                });

                // Components :
                frame.add(fontSizeLabel);
                frame.add(fontSize);
                frame.add(fontTypeLabel);
                frame.add(fontCombo);
                frame.add(textFormatLabel);
                frame.add(boldButton);
                frame.add(italicButton);
                frame.add(plainButton);
                frame.add(boldnitalicButton);
                frame.add(bulletsnlistsLabel);
                frame.add(bulletsnlistsBox);
                /*frame.add(bulletButton);
                frame.add(dashButton);*/

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
                JOptionPane.showMessageDialog(null, "Minotes\nAuthor : Andrei Datcu", "About",JOptionPane.PLAIN_MESSAGE);
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
                                File file;
                                FileWriter fw = new FileWriter(path);
                                fw.write(textPane1.getText());
                                fw.close();
                            }

                            catch (IOException ioe){
                                System.out.println("uh-oh something's not good");
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
                                BufferedReader br = new BufferedReader(new FileReader(path));
                                textPane1.setText("");
                                String line = null;
                                while ((line = br.readLine())!=null){
                                    textPane1.append(line + "\n");
                                }
                               // Scanner scan = new Scanner(file);
                               // while(scan.hasNextLine()){
                                //    textPane1.setText(scan.nextLine());
                               // }
                            } catch (IOException ioe) {
                                System.out.println("uh-oh something's not good");
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
        JFrame frame = new JFrame("Minotes");
        frame.setContentPane(new wordform().mainPanel);
        frame.setPreferredSize(new Dimension(500,500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
