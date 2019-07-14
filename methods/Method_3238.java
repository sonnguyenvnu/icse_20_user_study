public Vector divideToSelf(int n){
  for (int i=0; i < elementArray.length; i++) {
    elementArray[i]=elementArray[i] / n;
  }
  return this;
}
