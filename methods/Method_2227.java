private void prepareActualImageInBackground(FrescoState frescoState){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("FrescoControllerImpl#prepareActualImageInBackground");
  }
  Hierarcher hierarcher=mFrescoContext.getHierarcher();
  frescoState.setActualImageWrapper(hierarcher.buildActualImageWrapper(frescoState.getImageOptions()));
  frescoState.setOverlayDrawable(hierarcher.buildOverlayDrawable(frescoState.getResources(),frescoState.getImageOptions()));
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.endSection();
  }
}
