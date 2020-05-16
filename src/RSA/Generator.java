package RSA;

import java.math.BigInteger;
import java.security.SecureRandom;

import static RSA.Math.MillerRabinTest;
import static RSA.Math.relativelyPrime;


public class Generator {

    public static BigInteger generatePrime(){
        return generatePrime(128,application.Main.showSteps,3);
    }

    // This method can generate a prime number with a given size (in bits) and accuracy (number of tests).

    public static BigInteger generatePrime(int bitLength,boolean showSteps,int accuracy){

        SecureRandom secureRandom;
        if(showSteps){
            System.out.println("Generating a prime number, a size of: "+bitLength+" bit(s).");
        }

        BigInteger prime= BigInteger.ONE;
        int primesNumber=0;

        while(prime.equals(BigInteger.ONE)){
            secureRandom = new SecureRandom();
            prime= new BigInteger(bitLength,secureRandom);
            primesNumber++;
            if(showSteps){
                System.out.println("\nGenerated probable primes number: "+primesNumber);
            }
            for(int i=0; i<accuracy;i++){
                if(!MillerRabinTest(prime,showSteps)){
                    i=accuracy;
                    prime=BigInteger.ONE;
                    if(showSteps){
                        System.out.println("\nFailed on testing!");
                    }
                }
            }
        }

        return prime;
    }

    public static BigInteger generateRelativePrime(BigInteger n){
        for(BigInteger i=BigInteger.TWO.add(BigInteger.ONE);!(n.subtract(i).equals(BigInteger.ONE));i=i.add(BigInteger.TWO)){
            if(application.Main.showSteps) {
                System.out.println("RP cycle: " + i);
            }
            if(relativelyPrime(n,i)){
                return i;
            }
        }
        return BigInteger.ZERO;
    }

}
