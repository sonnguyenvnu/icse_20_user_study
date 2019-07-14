@SafeVarargs static <T extends EnumDict>boolean maskInAny(long mask,T... t){
  long value=toMask(t);
  return (mask & value) != 0;
}
