/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.googlesheetsexample;

/**
 *
 * @author Padilla
**/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GoogleSheetsViewer extends JFrame {

    public GoogleSheetsViewer() {
        setTitle("Google Sheets Viewer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);

        // Read the data from the Google Sheets file in CSV format
        List<String[]> data = readGoogleSheetsCSV("https://docs.google.com/spreadsheets/d/12xQN83UHl0WnlKHAsTMY2wuXiHiMw4sPqYcW4WJ9Lys/export?format=csv&id=12xQN83UHl0WnlKHAsTMY2wuXiHiMw4sPqYcW4WJ9Lys&gid=124346702");

        // Create a JTable and add it to the panel
        JTable table = new JTable(data.size(), data.get(0).length);
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).length; j++) {
                table.setValueAt(data.get(i)[j], i, j);
            }
        }
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
    }

    private List<String[]> readGoogleSheetsCSV(String urlString) {
        List<String[]> data = new ArrayList<>();
        try {
            URL url = new URL(urlString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                data.add(row);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        GoogleSheetsViewer viewer = new GoogleSheetsViewer();
        viewer.setVisible(true);
    }
}
