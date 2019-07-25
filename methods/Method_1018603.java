public byte[][] split(byte[] input){
  return Erasure.split(input,nOriginalFragments,nAllowedFailures);
}
