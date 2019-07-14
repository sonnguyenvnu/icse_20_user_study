@Override protected PipelineDraweeController obtainController(){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("PipelineDraweeControllerBuilder#obtainController");
  }
  try {
    DraweeController oldController=getOldController();
    PipelineDraweeController controller;
    final String controllerId=generateUniqueControllerId();
    if (oldController instanceof PipelineDraweeController) {
      controller=(PipelineDraweeController)oldController;
    }
 else {
      controller=mPipelineDraweeControllerFactory.newController();
    }
    controller.initialize(obtainDataSourceSupplier(controller,controllerId),controllerId,getCacheKey(),getCallerContext(),mCustomDrawableFactories,mImageOriginListener);
    controller.initializePerformanceMonitoring(mImagePerfDataListener);
    return controller;
  }
  finally {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
}
