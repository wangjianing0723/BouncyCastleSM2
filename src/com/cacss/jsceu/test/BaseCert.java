package com.cacss.jsceu.test;  
  
import com.cacss.jsceu.context.CAConfig;

import cn.com.suresec.jce.provider.SuresecProvider;
import sun.misc.BASE64Encoder;

import org.bouncycastle.jce.provider.BouncyCastleProvider;  
import org.bouncycastle.x509.X509V3CertificateGenerator;  
  
import javax.security.auth.x500.X500Principal;

import java.math.BigInteger;
import java.security.*;  
import java.security.cert.X509Certificate;
import java.util.Date;  
  
  
/** 
 * Created With IntelliJ IDEA. 
 * 
 * @author : lee 
 * @group : sic-ca 
 * @Date : 2014/12/30 
 * @Comments : ֤���� 
 * @Version : 1.0.0 
 */  
@SuppressWarnings("all")  
public class BaseCert {  
    /** 
     * BouncyCastleProvider 
     */  
    static {  
        Security.addProvider(new SuresecProvider()); 
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }  
    /** 
     *  
     */  
    protected static KeyPairGenerator kpg = null;  
  
    /**
     * @throws NoSuchProviderException  
 *  
 */  
    public BaseCert() throws NoSuchProviderException {  
        try {  
            //kpg = KeyPairGenerator.getInstance("EC","BC"); 
        	String alg ="SM2";
        	kpg = KeyPairGenerator.getInstance(alg);
            kpg.initialize(256);
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * ���� X509 ֤�� 
     * @param user 
     * @return 
     */  
    public X509Certificate generateCert(String user) {  
        X509Certificate cert = null;  
        try {  
            KeyPair keyPair = this.kpg.generateKeyPair();  
            PublicKey pubKey = keyPair.getPublic();  
            PrivateKey priKey = keyPair.getPrivate();  
            byte[] signature = sign(priKey, "123".getBytes());
            boolean a=verify(pubKey,signature,"123".getBytes());
            String sign=new BASE64Encoder().encode(signature);
            System.out.println(sign);
            System.out.println(a);
            X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();  
            BigInteger serialNumber = BigInteger.valueOf(System.currentTimeMillis());
            // �������к�  
            certGen.setSerialNumber(serialNumber);  
            // ���ð䷢��  
            certGen.setIssuerDN(new X500Principal(CAConfig.CA_ROOT_ISSUER));  
            // ������Ч��  
            Date startDate = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);  
            Date endDate = new Date(System.currentTimeMillis() + 365 * 24 * 60 * 60 * 1000); 
            certGen.setNotBefore(startDate);  
            certGen.setNotAfter(endDate);  
            // ����ʹ����  
            certGen.setSubjectDN(new X500Principal(CAConfig.CA_ROOT_ISSUER));  
            // ��Կ  
            certGen.setPublicKey(pubKey);  
            // ǩ���㷨  
            certGen.setSignatureAlgorithm(CAConfig.CA_SHA);  
            cert = certGen.generateX509Certificate(priKey, "BC");  
        } catch (Exception e) {  
            System.out.println(e.getClass() + e.getMessage());  
        }  
        return cert;  
    }  
    
    public static byte[] sign(PrivateKey privateKey, byte[] plainText)
    {
        try { 
            //ʵ����
            Signature signature = Signature.getInstance(CAConfig.CA_SHA);
            
            //��ʼ��������˽Կ
            signature.initSign(privateKey);
            
            //����
            signature.update(plainText);
            
            //ǩ��
            return signature.sign();
            
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static boolean verify(PublicKey publicKey, byte[] signatureVerify, byte[] plainText )
    {
        try {
            //ʵ����
            Signature signature = Signature.getInstance(CAConfig.CA_SHA);
            
            //��ʼ��
            signature.initVerify(publicKey);
            
            //����
            signature.update(plainText);
            
            //��ǩ
            return signature.verify(signatureVerify);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return false;
    }
}