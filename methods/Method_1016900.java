public double value(int labelIndex){
  return labelIndex == this.index ? weightOfLabel : 0;
}
