/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mehmet_atakan_kalkar_hw3;

/**
 *
 * @author ataka
 */
public class Position {
    public int row;
    public int col;
    
    public Position(){
        
    }
    
    public Position(int r,int c){
        row = r;
        col = c;
    }
    public int getrow(){
        return row;
    }
    public int getcol(){
        return col;
    }
    
}
