void addAfterInterceptorCallback(ModelInterceptorCallback callback){
  assertIsBuildingModels();
  if (modelInterceptorCallbacks == null) {
    modelInterceptorCallbacks=new ArrayList<>();
  }
  modelInterceptorCallbacks.add(callback);
}
