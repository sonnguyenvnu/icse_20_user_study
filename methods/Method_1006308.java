@Benchmark public BitSet bitset(){
  BitSet result=new BitSet();
  for (int i=0; i < bitSets.length; ++i) {
    BitSetWithOffset bit=bitSets[i];
    int currentBit=bit.bitset.nextSetBit(0);
    while (currentBit != -1) {
      result.set(currentBit + bit.offset);
      currentBit=bit.bitset.nextSetBit(currentBit + 1);
    }
  }
  return result;
}
