@OnPrepare static void onPrepare(ComponentContext context,@Prop(optional=true) final @Nullable Uri uri,@Prop(optional=true) final @Nullable MultiUri multiUri,@Prop(optional=true) final @Nullable ImageOptions imageOptions,@Prop(optional=true) final @Nullable FrescoContext frescoContext,@Prop(optional=true) final @Nullable Object callerContext,@Prop(optional=true) final @Nullable ImageListener imageListener,@State(canUpdateLazily=true) final FrescoState lastFrescoState,Output<FrescoState> frescoState){
  FrescoState maybeNewFrescoState=getController(context,frescoContext).onPrepare(lastFrescoState,uri,multiUri,imageOptions != null ? imageOptions : ImageOptions.defaults(),callerContext,context.getResources(),imageListener);
  if (lastFrescoState != maybeNewFrescoState) {
    FrescoVitoImage.lazyUpdateLastFrescoState(context,maybeNewFrescoState);
  }
  frescoState.set(maybeNewFrescoState);
}
