private void addAMatch(Node node,Object data){
  RuleContext ctx=(RuleContext)data;
  AtomicLong total=(AtomicLong)ctx.getAttribute(counterLabel);
  total.incrementAndGet();
  this.matches.add(node);
}
