
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Desikur
 */
public class MainAutomationPractice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Result res = JUnitCore.runClasses(login_page.class);
        for(Failure fai : res.getFailures()){
            System.out.println(fai.toString());
        }
    }
    
}
