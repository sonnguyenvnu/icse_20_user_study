public BitArray clone(){
  return new BitArray(Arrays.copyOfRange(bits,0,bits.length),size);
}
