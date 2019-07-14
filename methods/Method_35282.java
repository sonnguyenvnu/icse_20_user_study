@Override protected void onChangeStarted(@NonNull ControllerChangeHandler changeHandler,@NonNull ControllerChangeType changeType){
  super.onChangeStarted(changeHandler,changeType);
  setButtonsEnabled(false);
}
