package com.cacss.jsceu.context;  
  
/** 
 * Created With IntelliJ IDEA. 
 * 
 * @author : lee 
 * @group : sic-ca 
 * @Date : 2014/12/30 
 * @Comments : ≈‰÷√Ω”ø⁄ 
 * @Version : 1.0.0 
 */  
public interface CAConfig {  
  
    /** 
     * C 
     */  
    String CA_C = "CN";  
    /** 
     * ST 
     */  
    String CA_ST = "BJ";  
    /** 
     * L 
     */  
    String CA_L = "BJ";  
    /** 
     */                                                                                                                                                                                                                                    
    String CA_O = "SICCA";  
      
    /** 
     * CA_ROOT_ISSUER 
     */  
    String CA_ROOT_ISSUER="C=CN,ST=BJ,L=BJ,O=SICCA,OU=SC,CN=SICCA";  
    /** 
     * CA_DEFAULT_SUBJECT 
     */  
    String CA_DEFAULT_SUBJECT="C=CN,ST=BJ,L=BJ,O=SICCA,OU=SC,CN=";  
      
    //String CA_SHA="SHA256WithRSAEncryption";  
    String CA_SHA="1.2.156.10197.1.501";
   // String CA_SHA="SHA256withECDSA";
}