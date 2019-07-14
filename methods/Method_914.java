@Override public void addViolation(Object data,Node node,String arg){
  ViolationUtils.addViolationWithPrecisePosition(this,node,data,I18nResources.getMessage("java.other.AvoidNewDateGetTimeRule.violation.msg"));
}
