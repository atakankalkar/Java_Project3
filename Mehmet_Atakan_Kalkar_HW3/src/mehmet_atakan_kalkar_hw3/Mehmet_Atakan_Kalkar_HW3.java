/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mehmet_atakan_kalkar_hw3;

import java.io.IOException;

/**
 *
 * @author ataka
 */
public class Mehmet_Atakan_Kalkar_HW3 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        MazeClass obj = new MazeClass();
        obj.InputMaze("C:\\Users\\ataka\\Desktop\\mazehw.txt");
        obj.FindLoops();
     obj.FindLoops("C:\\Users\\ataka\\Desktop\\atakanson.txt");
        
}
}
