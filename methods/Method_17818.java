InternalNode consumeLayoutCreatedInWillRender(){
  final InternalNode layout=mLayoutCreatedInWillRender;
  mLayoutCreatedInWillRender=null;
  return layout;
}
