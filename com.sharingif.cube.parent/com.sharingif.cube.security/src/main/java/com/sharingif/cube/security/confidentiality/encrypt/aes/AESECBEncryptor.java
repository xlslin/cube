package com.sharingif.cube.security.confidentiality.encrypt.aes;

import java.io.UnsupportedEncodingException;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.sharingif.cube.core.exception.CubeRuntimeException;
import com.sharingif.cube.security.binary.BinaryCoder;
import com.sharingif.cube.security.confidentiality.encrypt.TextEncryptor;

/**
 * [AES对称加密]
 * [2015年4月7日 下午11:17:34]
 * [@author Joly]
 * [@version v1.0]
 * [@since v1.0]
 */
public class AESECBEncryptor extends AbstractAESEncryptor implements TextEncryptor {
	
    // 加解密算法/工作模式/填充方式,Java6.0支持PKCS5Padding填充方式,BouncyCastle支持PKCS7Padding填充方式
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
	
	public AESECBEncryptor() {
		super(CIPHER_ALGORITHM);
	}
	
	public AESECBEncryptor(BinaryCoder binaryCoder) {
		super(CIPHER_ALGORITHM, binaryCoder);
	}
	
	@Override
	protected IvParameterSpec createIvParameterSpec(SecretKey secretKey) {
		return null;
	}

	@Override
	public String encrypt(String text) {
		return getBinaryCoder().encode(encryptAES(text));
	}

	@Override
	public boolean supportsDecrypt() {
		return true;
	}

	@Override
	public String decrypt(String encryptedText) {
		try {
			return new String(decryptAES(encryptedText), getCharset());
		} catch (UnsupportedEncodingException e) {
			throw new CubeRuntimeException("aes ecb decrypt unsupported encoding error", e);
		}
	}

	@Override
	public boolean matches(String originalText, String encryptedText) {
		return (originalText).equals(decrypt(encryptedText));
	}
	
}
