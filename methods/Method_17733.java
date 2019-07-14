@Override protected void setupBinding(Resolver resolver){
  final SpringNode springNode=new SpringNode(mSpringConfig);
  final ConstantNode initial=new ConstantNode(resolver.getCurrentState(mPropertyAnimation.getPropertyHandle()));
  final ConstantNode end=new ConstantNode(mPropertyAnimation.getTargetValue());
  addBinding(initial,springNode,SpringNode.INITIAL_INPUT);
  addBinding(end,springNode,SpringNode.END_INPUT);
  addBinding(springNode,resolver.getAnimatedPropertyNode(mPropertyAnimation.getPropertyHandle()));
}
