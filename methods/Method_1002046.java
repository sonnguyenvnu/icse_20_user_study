private byte[] gcm(Mode mode,String info,byte[] nonce,byte[] data){
  try {
    Cipher cipher=Cipher.getInstance(ENCRYPTION_ALGORITHM,encryptionProvider);
    SecretKey derivedKey=deriveKey(cipher.getBlockSize(),info);
    GCMParameterSpec gcmParameters=new GCMParameterSpec(TAG_BITS,nonce);
    cipher.init(mode.cipherMode,derivedKey,gcmParameters);
    return cipher.doFinal(data);
  }
 catch (  IllegalBlockSizeException|InvalidAlgorithmParameterException|NoSuchPaddingException|NoSuchAlgorithmException|InvalidKeyException|BadPaddingException e) {
    throw Throwables.propagate(e);
  }
}
