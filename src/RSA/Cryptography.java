package RSA;

import application.Main;

import java.math.BigInteger;

import static RSA.Math.FME;

public class Cryptography {

    public static BigInteger decrypt(BigInteger c, Keys key) {
        if(Main.showSteps){
            System.out.println("Starting to decrypt message: "+c+" with key: "+key);
        }
        return FME(c, key.PrivateKey.getD(), key.PublicKey.n);
    }

    public static BigInteger encrypt(BigInteger m, Keys key) throws Exception {
        if(key.PublicKey.n.subtract(m).signum()==0 || key.PublicKey.n.subtract(m).signum()==-1)
        {
            throw new Exception("Error: Incorrect message size!");
        }
        if(Main.showSteps){
            System.out.println("Starting to encrypt message: "+m+" with key: "+key);
        }
        return FME(m, key.PublicKey.e, key.PublicKey.n);
    }


}
