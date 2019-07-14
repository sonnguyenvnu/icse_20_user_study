private int getDivided(int value){
  if (value >= 0) {
    return value / iDivisor;
  }
 else {
    return ((value + 1) / iDivisor) - 1;
  }
}
