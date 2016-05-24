package com.test.getui;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
 

import javax.crypto.Cipher;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
 
public class KeyPairTest {
    private static final String ALGOGRITHM = "RSA";
    private static final String PUBLIC_KEY_PATH = "public.key";
    private static final String PRIVATE_KEY_PATH = "private.key";
     
    public static void testGenerate() throws Exception {
        //KeyPairGenerator引擎类用于产生密钥对，JDK(7)默认支持的算法有，DiffieHellman、DSA、RSA、EC
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGOGRITHM);
        //产生密钥对
        KeyPair keyPair = generator.generateKeyPair();
        //获取公钥
        PublicKey publicKey = keyPair.getPublic();
        //获取私钥
        PrivateKey privateKey = keyPair.getPrivate();
         
        //将公钥与私钥写入文件，以备后用
        writeKey(PUBLIC_KEY_PATH, publicKey);
        writeKey(PRIVATE_KEY_PATH, privateKey);
    }
     
    public  static void testEncryptAndDecrypt() throws Exception {
        Cipher cipher = Cipher.getInstance(ALGOGRITHM);
        //读取公钥，进行加密
        PublicKey publicKey = (PublicKey) readKey(PUBLIC_KEY_PATH);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        //加密
        String sendInfo = "你,好";
        byte[] results = cipher.doFinal(sendInfo.getBytes());
         
        BASE64Encoder encoder = new BASE64Encoder();
        String msg=encoder.encode(results);
        System.out.println(msg);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b1 = decoder.decodeBuffer(msg);
       //读取撕钥，进行解密
        PrivateKey pri=(PrivateKey) readKey(PRIVATE_KEY_PATH);
        cipher.init(Cipher.DECRYPT_MODE, pri);
        //解密
        byte[] deciphered = cipher.doFinal(b1);
        //得到明文
        String recvInfo = new String(deciphered);
        System.out.println(recvInfo);
    }
     
    public static void writeKey(String path, Key key) throws Exception {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(key);
        oos.close();
    }
     
    public static Key readKey(String path) throws Exception {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream bis = new ObjectInputStream(fis);
        Object object = bis.readObject();
        bis.close();
        return (Key) object;	
    }
    
    
    public static void main(String[] args) throws Exception {
    	testGenerate();
	}
}