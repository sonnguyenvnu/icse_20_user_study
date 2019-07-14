@Override public void setImageURI(Uri uri,@Nullable Object callerContext){
  SimpleDraweeControllerBuilder controllerBuilder=getControllerBuilder().setUri(uri).setCallerContext(callerContext).setOldController(getController());
  if (controllerBuilder instanceof AbstractDraweeControllerBuilder) {
    ((AbstractDraweeControllerBuilder<?,?,?,?>)controllerBuilder).setControllerListener(mListener);
  }
  setController(controllerBuilder.build());
}
