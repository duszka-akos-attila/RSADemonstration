package RSA;

import java.math.BigInteger;

import static RSA.Generator.generatePrime;
import static RSA.Generator.generateRelativePrime;
import static RSA.Math.EEA;

public class Keys {

    private BigInteger p;
    private BigInteger q;
    public PublicKey PublicKey;
    PrivateKey PrivateKey;

    public Keys(){
        p=generatePrime();
        q=generatePrime();
        PublicKey=new PublicKey();
        PrivateKey=new PrivateKey();
        System.out.println("Public and Private keys created!");
    }
    public Keys(PublicKey publicKey){
        this.PublicKey= publicKey;
        System.out.println("Foreign key added!");

    }


    public class PublicKey{
        public BigInteger n;
        public BigInteger e;

        private PublicKey() {
            if(application.Main.showSteps) {
                System.out.println("Creating PK");
            }
            this.n = p.multiply(q);
            this.e = generateRelativePrime(p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)));
            if(application.Main.showSteps) {
                System.out.println("PK Over");
            }
        }
    }


    class PrivateKey{
        private BigInteger d;

        private PrivateKey(){
            if(application.Main.showSteps) {
                System.out.println("Creating SK");
            }
            BigInteger[] temp=EEA(PublicKey.e,p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)));
            if(temp[0].signum()>0){
                d = temp[0];
            }
            else{
                d=temp[0].add(p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)));
                if(application.Main.showSteps) {
                    System.out.println("Added Phi(n) to the d!");
                }
            }
        }

        public BigInteger getD() {
            return d;
        }
    }
}

