package com.cacss.jsceu.test;  
  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateEncodingException;  
import java.security.cert.X509Certificate;  
  
/** 
 * Created With IntelliJ IDEA. 
 * 
 * @author : lee 
 * @group : sic-ca 
 * @Date : 2014/12/30 
 * @Comments : 测试证书类 
 * @Version : 1.0.0 
 */  
public class GenerateCa {  
    private static String certPath = "d:/lee.cer";  
    public static void main(String[] args) throws NoSuchProviderException {  
        BaseCert baseCert = new BaseCert();  
        X509Certificate cert = baseCert.generateCert("Lee");  
        System.out.println(cert.toString());  
        
        // 导出为 cer 证书  
        try {  
            FileOutputStream fos = new FileOutputStream(certPath);  
            fos.write(cert.getEncoded());  
            fos.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (CertificateEncodingException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
    
}