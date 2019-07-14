private void buildAndSetComponentTree(boolean useRT){
  final ComponentContext context=new ComponentContext(this);
  final Component component=RTAnimationComponent.create(context).useRT(useRT).build();
  mLithoView.setComponentTree(ComponentTree.create(context,component).build());
}
