public void setListener(AbstractDraweeControllerBuilder controllerBuilder){
  if (mConfig.instrumentationEnabled) {
    controllerBuilder.setControllerListener(mListener);
  }
}
