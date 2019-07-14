private void checkType(AbstractJavaTypeNode node,Object data){
  if (node.getType() == Timer.class) {
    addViolationWithMessage(data,node,"java.concurrent.AvoidUseTimerRule.violation.msg");
  }
}
