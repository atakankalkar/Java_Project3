/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mehmet_atakan_kalkar_hw3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;



/**
 *
 * @author ataka
 */
public class MazeClass implements MazeInterface{
        
    	static char [][] maze;
	static int m;
        LinkedList list2 = new LinkedList();	
        Stack stack1 = new Stack();
        LinkedList ll = new LinkedList();
        Stack stack2 = new Stack();
        LinkedList printer2 = new LinkedList();
        FileWriter fw ;             //Defining filewriter and printwriter in order to writing output to the txt file.
        PrintWriter pw ;
        
        
 @Override
 public void InputMaze(String FileName) {
 
 try{
     FileReader fr = new FileReader(FileName);
     BufferedReader br = new BufferedReader(fr);
     String strsize;
     strsize = br.readLine();
     //System.out.println(strsize);
     String[] str = strsize.split(" ");
     int temparr [] = new int[str.length];           //Reads first line of the text file to take sizes of the maze array.
                                                       //[size1][size2]
     for(int i=0; i<temparr.length; i++) {
         temparr[i] = Integer.parseInt(str[i]);
      }
        //  System.out.println(Arrays.toString(temparr));    
          
          maze = new char[temparr[0]+2][temparr[1]+2];       //new char maze array obtained with size of [size1+2][size2+2].  
          m=temparr[0];
     String strmaze;
     int offset = 0;    
  
for (int i=1; i < temparr[0]+1 ; i++) {           //read other lines for getting maze array elements.                  
    strmaze = br.readLine();
         String replaceAll = strmaze.replaceAll("\\s+","");
    for (int j=1; j < temparr[1]+1 ; j++) {
       
        maze[i][j] = replaceAll.charAt(offset++);
        
    }
    offset=0;
    for(int t= 0;t<temparr[1]+2;t++){                   //process of filling the maze with question marks
    maze[0][t]='?';
    maze[temparr[0]+1][t]='?';
            }
    for(int g=0; g<temparr[0]+2;g++){
        maze[g][0]='?';
        maze[g][temparr[1]+1]='?';
    }
}      
/*
int a=0;
int y=temparr[1]+2;                                     //printing maze
        for(int k=0;k<temparr[0]+2;k++){
            for(int z=0;z<temparr[1]+2;z++){
                	//	System.out.print(maze[k][z] + " ");
                                a++;
                                if(a%y==0 && a!=0 ){
                          //          System.out.println("\n");
                                }
		     
            }
        }
     */
     br.close();
 }
     catch(IOException e){
             System.out.println("File could not be read");
             }
 
 
 }
 @Override
 public void FindLoops() {
     
                Position [] offset = new Position[4];//Position object to check right,down,left and up positions of current position.
		for(int i = 0; i <= 3; i++)
		offset[i] = new Position();
		offset[0].row = 0; 
		offset[0].col = 1; // right
		offset[1].row = 1;
		offset[1].col = 0; // down
		offset[2].row = 0; 
		offset[2].col = -1; // left
		offset[3].row = -1; 
		offset[3].col = 0; // up
                   Position here = new Position();
		   Position sabit = new Position();
                   int temp,temp2;
                   int r=1;
                   int c=1;
		   here.row = 1;
		   here.col = 1;
                   int option=0;
                   char result;                   
                   int count = 0;
                   int count2 = 0;
                  
                   
                    while(r!=m+1 || c!=1){      //Checks all elements of maze(left to right).
                        result=maze[r][c];      
                        sabit.row=r;
                        sabit.col=c;
                       
                            here.row=r;
                            here.col=c;
                            here=new Position(r,c);
                            ll.add(r);                      //Positions of all loops are stored in linkedlist
                            ll.add(c);
                            result++;    
                             int x=0;
                          
                            while(option<4){       //Checks all options of current place and if it founds new position it pushes 
                                                       //position to the stack.
                                here.row=r;
                                here.col=c;
                              int  oldr=r;
                              int  oldc=c;
                                
                       r = here.row + offset[option].row;
		       c = here.col + offset[option].col;
                       here.row=r;
                       here.col=c;
                       if(r==sabit.row && c==sabit.col){    //Checks loop is completed or not.        
                           int sz= ll.size();
                           if(sz>=8){                       //At least linked list have 4 position for a loop(4row+4column=8) 
                               count2++;                    //Counts for how many loop that our maze has.
                               for(int j=0;j<ll.size();j++){
                                   printer2.add(ll.get(j));      //Adds all loops to the another linked list for printing at the end.
                               }
                               list2.add(sz); 
                           }
                       }
                       if(maze[r][c]==result){             
                           x++;
                       here=new Position(r,c);   
                       stack1.push(here.row);       //All positions that our program can go are pushed to the stack1.
                       stack1.push(here.col);
                       here.row=r;
                       here.col=c;
                       if(x>1){                     //İf we have more than 1 position around our current position,
                       int size=ll.size();          //Store the size of current linkedlist in stack2.
                       stack2.push(size);
                       }
                       }
                       r=oldr;                 
                       c=oldc;
                   
                       option++;
                       if(option==4){           //If our program finds any way,it pops stack1 and add to linked list.
                           if(!stack1.isEmpty()){           
                               if(x==0 && !stack2.isEmpty()){  //If there is no way around ,check stack2 and try old options that we
                                                                    //pushed before
                                 int t= (int) stack2.pop();
                                 int size2=ll.size();
                                 for(int i=t;i<size2;i++){
                                    ll.removeLast();
                                 }
                               }
                             temp =  (int) stack1.pop();
                             temp2 = (int) stack1.pop();
                      
                                r= temp2;
                                c= temp;     
                              ll.add(temp2);
                              ll.add(temp);
                       
                              here.row=temp2;
                              here.col=temp;
                           
                            result=maze[r][c];
                              option=0;
                               x=0;
                               result++;
                            }
                           else{            //If there is no way and no other old options move right element and check it.
                               ll.clear();
                               stack2.clear();
                               r=sabit.row;
                               c=sabit.col;
                           }
                       }
                            }
                            option=0;
                        c++;  
                       if(c==m+1){
                       c=1;
                       r++;
                       }             
                    }
                    //Printing Section
                  System.out.println("This program has been written by : Mehmet Atakan Kalkar\n");
                  System.out.println("The maze has "+count2+" loops");
                               count++;
                               int j=0;           
                                      while(!list2.isEmpty()){
                                       while(count<count2+1){ 
                                           System.out.print("loop " + count + ": ");
                                            int size14=(int) list2.getFirst();
                                      while(j<size14-2){  
                                            System.out.print(printer2.get(j)+ "," +printer2.get(j+1) +" - "); 
                                            j=j+2; 
                                            if(j==size14-2){
                                              System.out.print(printer2.get(j)+ "," +printer2.get(j+1)); 
                                            }    
                                            
                                        }
                                      int dn=0;
                                      while(dn<size14){
                                          printer2.removeFirst();
                                          dn++;
                                      }                           
                                      j=0;
                                        System.out.println("\n");
                                         count++;
                                         list2.removeFirst();
                                       }
                                       
                                      }
                               
                           
                  
                   
                    
                    
                    
                   
                   
}
                     
                         
                      
         
 
 
                   
    
 
 @Override
 public void FindLoops(String FileName1) {
                try {
                    fw = new FileWriter(FileName1);
                     pw = new PrintWriter(fw);
                    Position [] offset = new Position[4];
                    for(int i = 0; i <= 3; i++)
                        offset[i] = new Position();
                    offset[0].row = 0;
                    offset[0].col = 1; // right
                    offset[1].row = 1;
                    offset[1].col = 0; // down
                    offset[2].row = 0;
                    offset[2].col = -1; // left
                    offset[3].row = -1;
                    offset[3].col = 0; // up
                    Position here = new Position();
                    Position sabit = new Position();
                    int temp,temp2;
                    int r=1;
                    int c=1;
                    here.row = 1;
                    here.col = 1;
                    int option=0;
                    char result;
                    int count = 0;
                    int count2 = 0;
                    while(r!=m+1 || c!=1){
                        result=maze[r][c];
                        sabit.row=r;
                        sabit.col=c;
                       
                        here.row=r;
                        here.col=c;
                        here=new Position(r,c);
                        ll.add(r);
                        ll.add(c);
                        result++;
                        int x=0;
                        
                        while(option<4){
                            here.row=r;
                            here.col=c;
                            int  oldr=r;
                            int  oldc=c;
                            
                            r = here.row + offset[option].row;
                            c = here.col + offset[option].col;
                            here.row=r;
                            here.col=c;
                            if(r==sabit.row && c==sabit.col){
                                int sz= ll.size();
                                if(sz>=8){
                                    count2++;
                                    for(int j=0;j<ll.size();j++){
                                        printer2.add(ll.get(j));
                                    }
                                    list2.add(sz);
                                }
                            }
                            if(maze[r][c]==result){
                                x++;
                                here=new Position(r,c);
                                stack1.push(here.row);
                                stack1.push(here.col);
                                here.row=r;
                                here.col=c;
                                if(x>1){
                                    int size=ll.size();
                                    stack2.push(size);
                                }
                            }
                            r=oldr;
                            c=oldc;
                            
                            option++;
                            if(option==4){
                                if(!stack1.isEmpty()){
                                    if(x==0 && !stack2.isEmpty()){
                                        int t= (int) stack2.pop();
                                        int size2=ll.size();
                                        for(int i=t;i<size2;i++){
                                            ll.removeLast();
                                        }
                                    }
                                    temp =  (int) stack1.pop();
                                    temp2 = (int) stack1.pop();
                                    
                                    r= temp2;
                                    c= temp;
                                    ll.add(temp2);
                                    ll.add(temp);
                                    
                                    here.row=temp2;
                                    here.col=temp;
                                    
                                    result=maze[r][c];
                                    option=0;
                                    x=0;
                                    result++;
                                }
                                else{
                                    ll.clear();
                                    stack2.clear();
                                    r=sabit.row;
                                    c=sabit.col;
                                }
                            }
                        }
                        option=0;
                        c++;
                        if(c==m+1){
                            c=1;
                            r++;
                        }
                    }
                    
                    pw.println("This program has been written by : Mehmet Atakan Kalkar\n");
                    pw.println("The maze has "+count2+" loops");
                    count++;
                    int j=0;
                    while(!list2.isEmpty()){
                        while(count<count2+1){
                           pw.print("loop " + count + ": ");
                            int size14=(int) list2.getFirst();
                            while(j<size14-2){
                                pw.print(printer2.get(j)+ "," +printer2.get(j+1) +" - ");
                                j=j+2;
                                if(j==size14-2){
                                    pw.print(printer2.get(j)+ "," +printer2.get(j+1));
                                }
                                
                            }
                            int dn=0;
                            while(dn<size14){
                                printer2.removeFirst();
                                dn++;
                            }
                            j=0;
                            pw.println("\n");
                            count++;
                            list2.removeFirst();
                        }
                                     
                    }
                } catch (IOException ex) {
                   System.out.println("Path couldn't be found");
                }
     pw.close();
 }
}

