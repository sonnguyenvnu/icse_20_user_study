@Override public void verify(Number value,Annotation annotation){
  Range range=getRange(annotation);
  checkArgument(range.lower() < range.upper(),"Range lower bound must be greater than upper bound.");
  double dblValue=value.doubleValue();
  checkArgument(dblValue >= range.lower() && dblValue <= range.upper(),String.format("Value must be in range [%f, %f]",range.lower(),range.upper()));
}
