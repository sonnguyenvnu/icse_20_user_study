public KeyPair getKeyPair(String alias,char[] password){
  try {
synchronized (lock) {
      if (store == null) {
synchronized (lock) {
          store=KeyStore.getInstance("jks");
          store.load(resource.getInputStream(),this.password);
        }
      }
    }
    RSAPrivateCrtKey key=(RSAPrivateCrtKey)store.getKey(alias,password);
    RSAPublicKeySpec spec=new RSAPublicKeySpec(key.getModulus(),key.getPublicExponent());
    PublicKey publicKey=KeyFactory.getInstance("RSA").generatePublic(spec);
    return new KeyPair(publicKey,key);
  }
 catch (  Exception e) {
    throw new IllegalStateException("Cannot load keys from store: " + resource,e);
  }
}
