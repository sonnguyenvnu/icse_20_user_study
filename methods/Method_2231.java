@Override public void onDetach(FrescoState frescoState){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("FrescoControllerImpl#onDetach");
  }
  frescoState.setAttached(false);
  if (mFrescoContext.getExperiments().closeDatasource()) {
    DeferredReleaser.getInstance().scheduleDeferredRelease(frescoState);
  }
  if (frescoState.getFrescoDrawable() != null) {
    frescoState.getFrescoDrawable().close();
    frescoState.setFrescoDrawable(null);
  }
  CloseableReference.closeSafely(frescoState.getCachedImage());
  frescoState.onRelease(frescoState.getId());
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.endSection();
  }
}
