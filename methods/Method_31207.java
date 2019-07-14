private BigInteger toBigInteger(String versionStr,String part){
  try {
    return new BigInteger(part);
  }
 catch (  NumberFormatException e) {
    throw new FlywayException("Version may only contain 0..9 and . (dot). Invalid version: " + versionStr);
  }
}
