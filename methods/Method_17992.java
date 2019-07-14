private ComponentContext getNewContextForLayout(ComponentContext context,@Nullable TreeProps treeProps,@Nullable LayoutStateFuture layoutStateFuture){
  final ComponentContext contextWithStateHandler;
synchronized (this) {
    final KeyHandler keyHandler=(ComponentsConfiguration.useGlobalKeys || ComponentsConfiguration.isDebugModeEnabled) ? new KeyHandler(mContext.getLogger()) : null;
    contextWithStateHandler=new ComponentContext(context,StateHandler.createNewInstance(mStateHandler),keyHandler,treeProps,layoutStateFuture);
  }
  return contextWithStateHandler;
}
