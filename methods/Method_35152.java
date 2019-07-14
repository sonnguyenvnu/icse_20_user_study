static boolean completeHandlerImmediately(@NonNull String controllerInstanceId){
  ChangeHandlerData changeHandlerData=inProgressChangeHandlers.get(controllerInstanceId);
  if (changeHandlerData != null) {
    changeHandlerData.changeHandler.completeImmediately();
    inProgressChangeHandlers.remove(controllerInstanceId);
    return true;
  }
  return false;
}
