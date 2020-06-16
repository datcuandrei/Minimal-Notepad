/*
        Author : Andrei Datcu
        Minotes.
        Project start date : 27-05-2020.
*/
package com.wordproc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import java.io.*;
import org.apache.commons.io.FilenameUtils;

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
                frame.setLocationRelativeTo(null);

                // Creating a menu for fonts :

                String[] fontItems = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
                final JComboBox fontCombo;
                fontCombo = new JComboBox(fontItems);
                fontCombo.setEditable(false);
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

                // Applying the font size :

                JButton fontSizeApply = new JButton("Apply");
                fontSizeApply.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String fontsItems = (String) fontCombo.getSelectedItem();
                        int sizeValue = fontSize.getValue();
                        Font appliedFont = textPane1.getFont();
                        Font sizeFont = new Font(fontsItems,appliedFont.getStyle(),sizeValue);
                        textPane1.setFont(sizeFont);
                        System.out.println("Font set to :" + sizeValue);
                    }
                });

                final JComboBox textFormat;
                String[] textFormatArray = {"Plain","Bold","Italic","Bold & Italic"};
                textFormat = new JComboBox(textFormatArray);
                textFormat.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String textFormatSelectedItem = (String) textFormat.getSelectedItem();
                        if (textFormatSelectedItem == "Plain"){
                            String fontItems = (String) fontCombo.getSelectedItem();
                            int sizeValue = fontSize.getValue();
                            Font plainFont = new Font(fontItems,Font.PLAIN,sizeValue);
                            textPane1.setFont(plainFont);
                            System.out.println("Text set to plain!");
                        }
                        if (textFormatSelectedItem == "Bold"){
                            String fontItems = (String) fontCombo.getSelectedItem();
                            int sizeValue = fontSize.getValue();
                            Font boldFont = new Font(fontItems,Font.BOLD,sizeValue);
                            textPane1.setFont(boldFont);
                            System.out.println("Text set to bold!");
                        }
                        if (textFormatSelectedItem == "Italic"){
                            String fontItems = (String) fontCombo.getSelectedItem();
                            int sizeValue = fontSize.getValue();
                            Font italicFont = new Font(fontItems,Font.ITALIC,sizeValue);
                            textPane1.setFont(italicFont);
                            System.out.println("Text set to italic!");
                        }
                        if (textFormatSelectedItem == "Bold & Italic"){
                            String fontItems = (String) fontCombo.getSelectedItem();
                            int sizeValue = fontSize.getValue();
                            Font boldnitalicFont = new Font(fontItems,Font.BOLD | Font.ITALIC,sizeValue);
                            textPane1.setFont(boldnitalicFont);
                            System.out.println("Text set to bold & italic!");
                        }
                    }
                });

                // Bullets & lists

                JLabel bulletsnlistsLabel;
                bulletsnlistsLabel = new JLabel("Punctuating lists");

                // Font size

                JLabel fontSizeLabel;
                fontSizeLabel = new JLabel("Size");

                // Font type

                JLabel fontTypeLabel;
                fontTypeLabel = new JLabel("Font");

                // Formatting

                JLabel textFormatLabel;
                textFormatLabel = new JLabel("Format");

                // Random space

                JLabel randomSpace;
                randomSpace = new JLabel("         ");

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
                frame.add(fontSizeApply);
                frame.add(fontTypeLabel);
                frame.add(fontCombo);
                frame.add(textFormatLabel);
                frame.add(textFormat);
                frame.add(randomSpace);
                frame.add(bulletsnlistsLabel);
                frame.add(bulletsnlistsBox);

                // Frame Preferences :

                frame.setPreferredSize(new Dimension(310,180));
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setLayout(new FlowLayout(FlowLayout.LEFT));
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }});

        // About button

        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Minotes b0.8.5\nAuthor : Andrei Datcu", "About",JOptionPane.PLAIN_MESSAGE);
            }
        });

        // Open and Save Chooser button

        opensaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Creating a frame for the options

                JFrame frame = new JFrame("File");
                frame.setLocationRelativeTo(null);

                // Public converting(just in case) :

                String textArea = (String) textPane1.getText();
                byte[] bytesArray = textArea.getBytes();

                //Save button :

                JButton saveButton;
                saveButton = new JButton("Save note");

                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // File saving algorithm :
                        JFileChooser choosePath = new JFileChooser();
                        choosePath.setDialogTitle("Choose where to save note");
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
                        choosePath.setDialogTitle("Choose note");
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

                frame.setPreferredSize(new Dimension(250,60));
                frame.setResizable(false);
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
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new wordform().mainPanel);
        frame.setPreferredSize(new Dimension(500,500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
