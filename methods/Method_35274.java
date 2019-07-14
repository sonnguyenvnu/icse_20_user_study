@Override protected void onChangeStarted(@NonNull ControllerChangeHandler changeHandler,@NonNull ControllerChangeType changeType){
  if (changeType.isEnter) {
    setTitle();
  }
}
