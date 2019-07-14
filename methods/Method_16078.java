@SafeVarargs static <T extends EnumDict>boolean maskIn(long mask,T... t){
  long value=toMask(t);
  return (mask & value) == value;
}
