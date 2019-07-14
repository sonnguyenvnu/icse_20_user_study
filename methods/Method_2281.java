@OnUnmount static void onUnmount(ComponentContext context,FrescoDrawable frescoDrawable,@Prop(optional=true) final @Nullable FrescoContext frescoContext,@FromPrepare final FrescoState frescoState){
  FrescoContext actualFrescoContext=resolveContext(context,frescoContext);
  if (actualFrescoContext.getExperiments().releaseInUnmount()) {
    frescoState.setFrescoDrawable(frescoDrawable);
    getController(context,frescoContext).onDetach(frescoState);
  }
}
