/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ChaletException;

/**
 *
 * @author lander
 */
public class FileLog {

    private static final String FILE = "C:\\Users\\lande\\OneDrive\\ChaletGithub\\chalet\\Chalet\\log.txt";

    public void logAction(String data) {
        System.err.println(data);

        try (PrintWriter out = new PrintWriter(new FileWriter(FILE, true))) {

            out.println(data);
            
        } catch (IOException ex) {
            Logger.getLogger(FileLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
