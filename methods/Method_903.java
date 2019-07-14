@Override public void addViolation(Object data,Node node,String arg){
  ViolationUtils.addViolationWithPrecisePosition(this,node,data,"java.oop.BigDecimalAvoidDoubleConstructorRule.violation.msg");
}
