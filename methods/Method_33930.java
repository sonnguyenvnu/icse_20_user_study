static String enc(CipherMetadata cipher){
  if (!cipher.algorithm().equalsIgnoreCase("AES/CBC/PKCS5Padding")) {
    throw new IllegalArgumentException("Unknown or unsupported algorithm");
  }
  if (cipher.keySize() == 128) {
    return "A128CBC";
  }
 else   if (cipher.keySize() == 256) {
    return "A256CBC";
  }
 else {
    throw new IllegalArgumentException("Unsupported key size");
  }
}
