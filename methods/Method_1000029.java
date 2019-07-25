public void mul(DataWord word){
  BigInteger result=value().multiply(word.value());
  this.data=ByteUtil.copyToArray(result.and(MAX_VALUE));
}
