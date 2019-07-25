public UnsignedVariableInteger increment(boolean rolloverToOne){
  if (value + 1 > getBits().getMaxValue()) {
    value=rolloverToOne ? 1 : 0;
  }
 else {
    value++;
  }
  return this;
}
