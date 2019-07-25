public static <T extends Cborable>PaddedAsymmetricCipherText build(SecretBoxingKey from,PublicBoxingKey to,T secret,int paddingBlockSize){
  byte[] cipherText=to.encryptMessageFor(PaddedCipherText.pad(secret.serialize(),paddingBlockSize),from);
  return new PaddedAsymmetricCipherText(new AsymmetricCipherText(cipherText));
}
