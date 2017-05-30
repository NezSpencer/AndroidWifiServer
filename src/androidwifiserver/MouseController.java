/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package androidwifiserver;

/**
 *
 * @author nezspencer
 */
import java.awt.*;
import java.awt.event.InputEvent;
public class MouseController {
    
    private Robot robot;
    public MouseController() throws AWTException{
        
        robot = new Robot();
        robot.setAutoDelay(40);
        robot.setAutoWaitForIdle(true);
    }
    
    public void mouseMouse(int x, int y){
    
//        robot.delay(1000);
        robot.mouseMove(x, y);
//        robot.delay(500);
    }
    
    public void performMouseClick(String whichButton){
    
        if(whichButton.equalsIgnoreCase("left")){
       
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//            robot.delay(200);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }
        else {
        
            //right mouse button
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
//            robot.delay(200);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
        }
        
        
    }
    
    public void passMouseOperation(String operation){
    
        if(operation.equalsIgnoreCase("left")){
        
            performMouseClick("left");
        }
        else if(operation.equalsIgnoreCase("right")){
        
            performMouseClick("right");
        }
        
        else{
        
            //mouse coordinates
            int x = 0;
            int y =0;
            
            String op[] = operation.trim().split("_");
            
            x = Integer.parseInt(op[0]);
            y = Integer.parseInt(op[1]);
            
            mouseMouse(x, y);
        }
    }
}
