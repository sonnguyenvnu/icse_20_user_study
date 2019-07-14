static ECPublicKey createPublicKey(final BigInteger x,final BigInteger y,final String curve){
  ECNamedCurveParameterSpec curveParameterSpec;
  if ((curveParameterSpec=ECNamedCurveTable.getParameterSpec(curve)) == null) {
    throw new IllegalArgumentException("Unsupported named curve: " + curve);
  }
  ECParameterSpec parameterSpec=new ECNamedCurveSpec(curveParameterSpec.getName(),curveParameterSpec.getCurve(),curveParameterSpec.getG(),curveParameterSpec.getN());
  ECPublicKeySpec publicKeySpec=new ECPublicKeySpec(new ECPoint(x,y),parameterSpec);
  try {
    return (ECPublicKey)KeyFactory.getInstance("EC").generatePublic(publicKeySpec);
  }
 catch (  Exception ex) {
    throw new RuntimeException(ex);
  }
}
