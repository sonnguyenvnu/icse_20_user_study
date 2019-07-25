protected Container ilazyor(ArrayContainer x){
  if (isFull()) {
    return this;
  }
  return ilazyorToRun(x);
}
