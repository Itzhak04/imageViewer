package software.ulpgc.imageviewer.swing;

import software.ulpgc.imageviewer.FileImageLoader;
import software.ulpgc.imageviewer.Image;
import software.ulpgc.imageviewer.NextImageCommand;
import software.ulpgc.imageviewer.PreviousImageCommand;
import software.ulpgc.imageviewer.swing.MainFrame;

import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {

        MainFrame frame = new MainFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int selection = fileChooser.showOpenDialog(frame);
        if (selection == JFileChooser.APPROVE_OPTION)
        {
            File folder = fileChooser.getSelectedFile();
            Image image = new FileImageLoader(folder).load();
            frame.add("<", new PreviousImageCommand(frame.imageDisplay()));
            frame.imageDisplay().show(image);
            frame.add(">", new NextImageCommand(frame.imageDisplay()));
        }else if (selection==JFileChooser.CANCEL_OPTION){
            System.exit(0);
        }
        frame.setVisible(true);
    }}
