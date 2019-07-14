static String keyEncryptionAlg(String javaName){
  String alg=javaToKeyAlgs.get(javaName);
  if (alg == null) {
    throw new IllegalArgumentException("Invalid or unsupported key encryption algorithm: " + javaName);
  }
  return alg;
}
