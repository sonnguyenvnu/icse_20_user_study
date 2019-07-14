private void renderViolation(ViolationNode vnode){
  vnode.getParent().addNumberOfViolation(1);
  RuleViolation vio=vnode.getRuleViolation();
  classBuf.append("<tr>" + " <td>" + vio.getMethodName() + "</td>" + " <td>" + this.displayRuleViolation(vio) + "</td>" + "</tr>");
}
