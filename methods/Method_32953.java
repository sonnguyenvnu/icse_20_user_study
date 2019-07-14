public String nextSessionId(){
  return new BigInteger(50,random).toString(16);
}
