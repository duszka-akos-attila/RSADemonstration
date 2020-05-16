package RSA;

import java.math.BigInteger;

public class Messages {
    public static class Message {
        private String message;
        private BigInteger chars;
        private boolean encrypted;

        public Message(String message, boolean encrypted) {
            this.message = message;
            this.chars = toNumbers(message);
            this.encrypted = encrypted;
        }

        public String getMessage() {
            return message;
        }

        public BigInteger getChars() {
            return chars;
        }

        public boolean isEncrypted() {
            return encrypted;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setChars(BigInteger chars) {
            this.chars = chars;
        }

        public void setEncrypted(boolean encrypted) {
            this.encrypted = encrypted;
        }
    }


    public static BigInteger toNumbers(String message){
        int digitCounter=3;
        BigInteger chars=BigInteger.valueOf(999);
        char[] characters=message.toCharArray();
        for (char character : characters) {
            if ((int) character >99) {
                chars = chars.multiply(BigInteger.TEN);
                chars = chars.add(BigInteger.valueOf(3));
                chars = chars.multiply(BigInteger.valueOf(1000));
                chars = chars.add(BigInteger.valueOf((int) character));
                digitCounter+=4;

            } else if ((int) character > 9) {
                chars = chars.multiply(BigInteger.TEN);
                chars = chars.add(BigInteger.TWO);
                chars = chars.multiply(BigInteger.valueOf(100));
                chars = chars.add(BigInteger.valueOf((int) character));
                digitCounter+=3;

            } else{
                chars = chars.multiply(BigInteger.TEN);
                chars = chars.add(BigInteger.ONE);
                chars = chars.multiply(BigInteger.valueOf(10));
                chars = chars.add(BigInteger.valueOf((int) character));
                digitCounter+=2;

            }
        }

        chars=chars.multiply(BigInteger.valueOf(1000));
        chars=chars.add(BigInteger.valueOf(999));
        digitCounter+=3;

        if(digitCounter>999)
        {
            digitCounter+=4;
            chars=chars.multiply(BigInteger.valueOf(10000));
            chars=chars.add(BigInteger.valueOf(digitCounter));

        }
        else if(digitCounter>99)
        {
            digitCounter+=3;
            chars=chars.multiply(BigInteger.valueOf(1000));
            chars=chars.add(BigInteger.valueOf(digitCounter));

        }
        else if(digitCounter>9)
        {
            digitCounter+=2;
            chars=chars.multiply(BigInteger.valueOf(100));
            chars=chars.add(BigInteger.valueOf(digitCounter));

        }
        else
        {
            digitCounter++;
            chars=chars.multiply(BigInteger.valueOf(10));
            chars=chars.add(BigInteger.valueOf(digitCounter));

        }


        return chars;
    }

    public static String toCharArray(BigInteger messageNumberFrom){
        return "";
    }

}

