private int getArg(int index,int defaultValue,boolean treatZeroAsDefault){
  int result=mArgs[index];
  if (result < 0 || (result == 0 && treatZeroAsDefault)) {
    result=defaultValue;
  }
  return result;
}
