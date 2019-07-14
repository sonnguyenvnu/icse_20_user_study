static String sigAlg(String javaName){
  String alg=javaToSigAlgs.get(javaName);
  if (alg == null) {
    throw new IllegalArgumentException("Invalid or unsupported signature algorithm: " + javaName);
  }
  return alg;
}
