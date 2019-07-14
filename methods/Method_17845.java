static ComponentContext withComponentTree(ComponentContext context,ComponentTree componentTree){
  ComponentContext componentContext=new ComponentContext(context,new StateHandler(),null,null,null);
  componentContext.mComponentTree=componentTree;
  componentContext.mComponentScope=null;
  componentContext.mLayoutStateFuture=null;
  return componentContext;
}
