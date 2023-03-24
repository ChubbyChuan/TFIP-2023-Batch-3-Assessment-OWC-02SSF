package ibf2022.batch2.ssf.frontcontroller.model;

import java.util.Random;

public class Captcha {

    private char operator;




    Random rand = new Random();

    private int num1 = rand.nextInt(50);
    private int num2 = rand.nextInt(50);
    private int charselector = rand.nextInt(4);
    private float result;
    
    public Captcha() {
        switch (charselector){
            case 0: this.operator = '+';
                    break;
            case 1: this.operator = '-';
                    break;
            case 2: this.operator = '*';
                    break;
            case 3: this.operator = '/';
                    break;
            default: this.operator = '?';
          }
    }

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public void generate(){
        switch (charselector){
            case 0: this.operator = '+';
                    this.result = num1 + num2;
                    break;
            case 1: this.operator = '-';
                     this.result = num1 - num2;
                    break;
            case 2: this.operator = '*';
                    this.result = num1 * num2;
                    break;
            case 3: this.operator = '/';
                    this.result = num1 / num2;
                    break;
            default: this.operator = '?';
          }
    }
    public boolean verify(float input) {
        if (this.result == input){
            return true;
        } else {
        return false;
        }
    }
    
}
