public BreakpointLocation compute(){
  SNode node=myLocation.resolve(myRepo);
  return (node == null ? new BreakpointLocation(myLocation) : new BreakpointLocation(node));
}
