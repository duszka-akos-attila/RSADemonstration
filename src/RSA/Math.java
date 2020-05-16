package RSA;

import application.Main;

import java.math.BigInteger;
import java.util.ArrayList;

public class Math {

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        BigInteger t;
        while(!b.equals(BigInteger.ZERO)){
            t = a;
            a = b;
            b = t.remainder(b);
        }
        return a;
    }

    public static boolean relativelyPrime(BigInteger greaterNumber, BigInteger smallerNumber) {
        return gcd(greaterNumber,smallerNumber).equals(BigInteger.ONE);
    }

    //The Miller-Robin test
    public static boolean MillerRabinTest(BigInteger prime, boolean logging){

        if(logging) {
            System.out.println("\nMiller-Rabin");
            System.out.println();
        }


        //Generating a random base for the test from 2 to the Integer roof 2147483647
        BigInteger base=BigInteger.valueOf((int)(java.lang.Math.random()*2147483646)+2);

        //Creating the variables for the repeated division by two
        BigInteger remainder=prime.subtract(BigInteger.ONE);
        int iterations=0;

        //Extracting the 2 from the prime factoryzation of @prime
        while((remainder.remainder(BigInteger.TWO).equals(BigInteger.ZERO))){
            iterations++;
            remainder=remainder.divide(BigInteger.TWO);
        }

        if(logging) {
            System.out.println("PRIME: "+prime);
            System.out.println("BASE: "+base);
            System.out.println("REMAINDER: " + remainder);
            System.out.println("ITERATIONS: "+iterations);
        }

        //Powering the random @base to the (2^@iteration)*@remainder and if mod @prime is 1
        for(int i=0; i<iterations;i++) {

            if(logging) {
                System.out.println();
                System.out.println(i+1+" / "+iterations+" iteration");
            }

            BigInteger result=base.modPow(remainder.multiply(BigInteger.TWO.pow(i)),prime);

            if(logging) {
                System.out.println(i+1+". RESULT: "+result);
            }

            if(result.equals(BigInteger.ONE)){
                return false;
            }
            if(i==iterations-1 && result.equals(prime.subtract(BigInteger.ONE))){
                if(logging) {
                    System.out.println("The last result = prime-1");
                }
                return true;
            }
        }

        return false;
    }
    //Extended Euclidean Algorithm
    public static BigInteger[] EEA(BigInteger a, BigInteger b){

        if(application.Main.showSteps) {
            System.out.println("Started EEA");
        }

        BigInteger x = BigInteger.ZERO;
        BigInteger y = BigInteger.ONE;
        BigInteger lastX = BigInteger.ONE;
        BigInteger lastY = BigInteger.ZERO;
        BigInteger temp;

        while (!b.equals(BigInteger.ZERO))
        {
            BigInteger[] quotientAndRemainder = a.divideAndRemainder(b);
            BigInteger quotient = quotientAndRemainder[0];

            a = b;
            b = quotientAndRemainder[1];

            temp = x;
            x = lastX.subtract(quotient.multiply(x));
            lastX = temp;

            temp = y;
            y = lastY.subtract(quotient.multiply(y));
            lastY = temp;
            if(application.Main.showSteps) {
                System.out.println("EEA Cycle");
            }
        }
        if(application.Main.showSteps) {
            System.out.println("EEA Over");
        }
        return new BigInteger[]{ lastX, lastY };
    }

    //Fast Modular Exponential algorithm
    public static BigInteger FME(BigInteger base,BigInteger exponent,BigInteger modulo){
        BigInteger result;
        BigInteger q=exponent;
        ArrayList<Integer> remainderPositions=new ArrayList<>();
        ArrayList<BigInteger> remainders=new ArrayList<>();
        remainders.add(base);
        if(Main.showSteps){
            System.out.println("STARTING FME");
        }
        for(int i=0;!(q.equals(BigInteger.ZERO));i++){
            if(q.remainder(BigInteger.TWO).equals(BigInteger.ONE)){
                remainderPositions.add(i);
                if(Main.showSteps) {
                    //System.out.println("FME remainder pos: " + remainderPositions.get(i));
                }

            }
            q=q.divide(BigInteger.TWO);
            if(Main.showSteps) {
                System.out.println("FME quatiant: " + q);
            }
        }

        BigInteger newBase=base;
        for(int i=0;i<remainderPositions.get(remainderPositions.size()-1)+1;i++){
            newBase=newBase.modPow((BigInteger.TWO),modulo);
            remainders.add(newBase);
            if(Main.showSteps) {
                System.out.println("FME remainders: " + remainders.get(i));
            }
        }


        result=remainders.get(0);
        if(Main.showSteps){
            System.out.println("First result: "+result);
        }
        for(int i=1; i<remainderPositions.size();i++){
            result=result.multiply(remainders.get(remainderPositions.get(i)));
            if(Main.showSteps) {
                System.out.println("FME remainderPositions: "+remainderPositions.get(i));
                System.out.println("FME result: " + result);
            }
        }
        return result.mod(modulo);
    }
}
