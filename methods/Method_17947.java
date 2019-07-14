private static LithoHandler ensureAndInstrumentLayoutThreadHandler(@Nullable LithoHandler handler){
  if (handler == null) {
    handler=ComponentsConfiguration.threadPoolForBackgroundThreadsConfig == null ? new DefaultLithoHandler(getDefaultLayoutThreadLooper()) : new ThreadPoolLayoutHandler(ComponentsConfiguration.threadPoolForBackgroundThreadsConfig);
  }
 else {
    if (sDefaultLayoutThreadLooper != null && sBoostPerfLayoutStateFuture == false && ComponentsConfiguration.boostPerfLayoutStateFuture == true && ComponentsConfiguration.perfBoosterFactory != null) {
      LithoPerfBooster booster=ComponentsConfiguration.perfBoosterFactory.acquireInstance();
      booster.markImportantThread(new Handler(sDefaultLayoutThreadLooper));
      sBoostPerfLayoutStateFuture=true;
    }
  }
  return instrumentLithoHandler(handler);
}
