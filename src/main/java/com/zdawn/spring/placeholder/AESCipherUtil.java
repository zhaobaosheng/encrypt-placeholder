package com.zdawn.spring.placeholder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESCipherUtil {
    //算法名称 
    public static final String KEY_ALGORITHM = "AES";
    //算法名称/加密模式/填充方式 
    public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    //密钥 
    private static String secretKey = "be7e2f5d083892b2";
    //向量
    private static String ivParameter ="WODEIVMIMAPARA23";
                                       
    public static byte[] encode(byte[] data) throws Exception {
    	SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"),KEY_ALGORITHM); 
        IvParameterSpec dps = new IvParameterSpec(ivParameter.getBytes("UTF-8"));
        //创建密码器
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        //初始化
        cipher.init(Cipher.ENCRYPT_MODE,keySpec,dps);
        
        byte[] enData = cipher.doFinal(data);
        
        return enData;
    }
    
    @SuppressWarnings("restriction")
	public static String encode(String data) throws Exception {
    	byte[] enData = encode(data.getBytes("UTF-8"));
    	sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    	return encoder.encode(enData);
    }

    public static byte[] decode(byte[] data) throws Exception {
    	SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"),KEY_ALGORITHM); 
        IvParameterSpec dps = new IvParameterSpec(ivParameter.getBytes("UTF-8"));
        //创建密码器
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        //初始化
        cipher.init(Cipher.DECRYPT_MODE,keySpec,dps);
        
        byte[] deData = cipher.doFinal(data);
        
        return deData;
    }
    @SuppressWarnings("restriction")
    public static String decode(String data) throws Exception {
    	sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
    	byte[] base64Decode = decoder.decodeBuffer(data);
    	byte[] deData = decode(base64Decode);
    	return new String(deData,"UTF-8");
    }
    
    public static void initSecretKey(String secretKey){
    	AESCipherUtil.secretKey = secretKey;
    }
    public static void initIvParameter(String ivParameter){
    	AESCipherUtil.ivParameter = ivParameter;
    }
    /**
     * 密钥 16个非中文字符
     */
    public void setSecretKey(String secretKey) {
		AESCipherUtil.secretKey = secretKey;
	}
    /**
     * 向量 16个非中文字符
     * @param secretKey
     */
	public void setIvParameter(String ivParameter) {
		AESCipherUtil.ivParameter = ivParameter;
	}

	public static void main(String[] args) throws Exception {
    	String data = args.length==0 ? null:args[0];
    	if(data==null || data.equals("")){
    		System.out.println("please input encrypt data");
    	}else{
    		String encodeData = AESCipherUtil.encode(data);
    		System.out.println("data="+data);
    		System.out.println("encrypt data="+encodeData);
    	}
    }
}