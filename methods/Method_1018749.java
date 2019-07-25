@Override SQLServerEncryptionAlgorithm create(SQLServerSymmetricKey columnEncryptionKey,SQLServerEncryptionType encryptionType,String encryptionAlgorithm) throws SQLServerException {
  assert (columnEncryptionKey != null);
  if (encryptionType != SQLServerEncryptionType.Deterministic && encryptionType != SQLServerEncryptionType.Randomized) {
    MessageFormat form=new MessageFormat(SQLServerException.getErrString("R_InvalidEncryptionType"));
    Object[] msgArgs={encryptionType,encryptionAlgorithm,"'" + SQLServerEncryptionType.Deterministic + "," + SQLServerEncryptionType.Randomized + "'"};
    throw new SQLServerException(this,form.format(msgArgs),null,0,false);
  }
  StringBuilder factoryKeyBuilder=new StringBuilder();
  factoryKeyBuilder.append(Base64.getEncoder().encodeToString(new String(columnEncryptionKey.getRootKey(),UTF_8).getBytes()));
  factoryKeyBuilder.append(":");
  factoryKeyBuilder.append(encryptionType);
  factoryKeyBuilder.append(":");
  factoryKeyBuilder.append(algorithmVersion);
  String factoryKey=factoryKeyBuilder.toString();
  SQLServerAeadAes256CbcHmac256Algorithm aesAlgorithm;
  if (!encryptionAlgorithms.containsKey(factoryKey)) {
    SQLServerAeadAes256CbcHmac256EncryptionKey encryptedKey=new SQLServerAeadAes256CbcHmac256EncryptionKey(columnEncryptionKey.getRootKey(),SQLServerAeadAes256CbcHmac256Algorithm.algorithmName);
    aesAlgorithm=new SQLServerAeadAes256CbcHmac256Algorithm(encryptedKey,encryptionType,algorithmVersion);
    encryptionAlgorithms.putIfAbsent(factoryKey,aesAlgorithm);
  }
  return encryptionAlgorithms.get(factoryKey);
}
