@Override public Money compute(Object parm){
  return myLeftOperand.compute(parm).multiply(myRightOperand.compute(parm));
}
