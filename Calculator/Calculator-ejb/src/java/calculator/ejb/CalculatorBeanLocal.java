/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator.ejb;

import javax.ejb.Local;

/**
 *
 * @author Fadhli Hisyam
 */
@Local
public interface CalculatorBeanLocal {

    public double add(double value);
    public double getTotal();
    int getCount();
    double getPlus();
    double getMinus();
    double getMul();
    double getDiv();
}
