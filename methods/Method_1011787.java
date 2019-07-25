@Override public Money compute(Object parm){
  return myLeftOperand.compute(parm).add(myRightOperand.compute(parm));
}
