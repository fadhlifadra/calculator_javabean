/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator.ejb;

import javax.ejb.Stateful;

/**
 *
 * @author Fadhli Hisyam
 */
@Stateful
public class CalculatorBean implements CalculatorBeanLocal {
    
    private double total = 0;
    private int count = 0;
          
    @Override
    public double sub(double value) {
        total -= value;
        count++;
        return total;
    }

    @Override
    public double add(double value) {
        total += value;
        count++;
        return total;
    }

    @Override
    public double multiple(double value) {
        total *= value;
        count++;
        return total;
    }

    @Override
    public double div(double value) {
        total /= value;
        count++;
        return total;
    }

    @Override
    public double getTotal() {
        return total;
    }
    
    
}
