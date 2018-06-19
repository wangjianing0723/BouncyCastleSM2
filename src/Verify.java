import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import com.cacss.jsceu.context.CAConfig;

import sun.misc.BASE64Decoder;

public class Verify {

	public static void main(String[] args) throws IOException, CertificateException, NoSuchProviderException {
		// TODO Auto-generated method stub

		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		String Filestr="D://root.cer";
		File file =new File(Filestr);
		FileInputStream fis=new FileInputStream(file);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		ByteArrayInputStream rootinput = new ByteArrayInputStream(buffer);
		CertificateFactory cf1 = null;
		cf1 = CertificateFactory.getInstance("X.509","BC");
		 X509Certificate root = null;
		 root = (X509Certificate)cf1.generateCertificate(rootinput);
		 System.out.println(root.getPublicKey());
		 byte[] sign=new BASE64Decoder().decodeBuffer("MEUCIA1rTO2QRN9OHuzEkiEx1dF0cbADu5g8RktxIu7XnGrgAiEApr7mS4XaKqt1oZcKaOa2qLDP0MdbM3kVU2dUsEImwFc=");
		 boolean c =verify(root.getPublicKey(),sign,"123".getBytes());
		 System.out.println(c);
		 
		 
	}
    public static boolean verify(PublicKey publicKey, byte[] signatureVerify, byte[] plainText )
    {
        try {
            //实例化
            Signature signature = Signature.getInstance(CAConfig.CA_SHA);
            
            //初始化
            signature.initVerify(publicKey);
            
            //更新
            signature.update(plainText);
            
            //验签
            return signature.verify(signatureVerify);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return false;
    }
}
