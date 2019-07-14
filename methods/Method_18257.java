private static TestOutput createTestOutput(InternalNode node,LayoutState layoutState,LayoutOutput layoutOutput){
  final int l=layoutState.mCurrentX + node.getX();
  final int t=layoutState.mCurrentY + node.getY();
  final int r=l + node.getWidth();
  final int b=t + node.getHeight();
  final TestOutput output=new TestOutput();
  output.setTestKey(node.getTestKey());
  output.setBounds(l,t,r,b);
  output.setHostMarker(layoutState.mCurrentHostMarker);
  if (layoutOutput != null) {
    output.setLayoutOutputId(layoutOutput.getId());
  }
  return output;
}
