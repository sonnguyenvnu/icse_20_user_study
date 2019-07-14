@OnPrepare static void onPrepare(ComponentContext c,@Nullable @Prop(optional=true) final EventHandler refreshHandler,Output<OnRefreshListener> onRefreshListener){
  if (refreshHandler != null) {
    onRefreshListener.set(new OnRefreshListener(){
      @Override public void onRefresh(){
        Recycler.dispatchPTRRefreshEvent(refreshHandler);
      }
    }
);
  }
}
