public void addmod(DataWord word1,DataWord word2){
  if (word2.isZero()) {
    this.data=new byte[32];
    return;
  }
  BigInteger result=value().add(word1.value()).mod(word2.value());
  this.data=ByteUtil.copyToArray(result.and(MAX_VALUE));
}
