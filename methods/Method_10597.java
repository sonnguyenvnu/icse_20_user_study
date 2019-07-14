private static String getAlgorithms(boolean rsa2){
  return rsa2 ? SIGN_SHA256RSA_ALGORITHMS : SIGN_ALGORITHMS;
}
