@OnMount static void onMount(ComponentContext context,final FrescoDrawable frescoDrawable,@Prop(optional=true) final @Nullable FrescoContext frescoContext,@Prop(optional=true) final @Nullable ImageListener imageListener,@FromPrepare final FrescoState frescoState){
  frescoState.setFrescoDrawable(frescoDrawable);
  getController(context,frescoContext).onAttach(frescoState,imageListener);
}
