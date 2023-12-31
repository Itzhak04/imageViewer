package software.ulpgc.imageviewer;

import javax.swing.*;
import java.io.File;

public class Main {
    public static final String root = "C://Users//itzha//Downloads//fotos";
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        Image image = new FileImageLoader(new File(root)).load();
        JPanel panel=new JPanel();
        frame.add("<", new PreviousImageCommand(frame.imageDisplay()));
        frame.imageDisplay().show(image);
        frame.add(">", new NextImageCommand(frame.imageDisplay()));
        frame.setVisible(true);
    }}
