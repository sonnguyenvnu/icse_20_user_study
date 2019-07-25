@VisibleForTesting static void generate(char[] password,Path destination,int keySize,String alias,SecureRandom random) throws Exception {
  KeyGenerator generator=KeyGenerator.getInstance("AES");
  generator.init(keySize,random);
  SecretKey key=generator.generateKey();
  KeyStore keyStore=KeyStore.getInstance("JCEKS");
  keyStore.load(null);
  keyStore.setKeyEntry(alias,key,password,null);
  try (OutputStream out=Files.newOutputStream(destination)){
    keyStore.store(out,password);
  }
 }
