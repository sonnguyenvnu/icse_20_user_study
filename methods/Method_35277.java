@Override protected void onChangeStarted(@NonNull ControllerChangeHandler changeHandler,@NonNull ControllerChangeType changeType){
  setOptionsMenuHidden(!changeType.isEnter);
  if (changeType.isEnter) {
    setTitle();
  }
}
