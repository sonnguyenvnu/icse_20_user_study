@Override public void saveToBundle(@NonNull Bundle bundle){
  super.saveToBundle(bundle);
  if (changeHandler != null) {
    bundle.putString(KEY_CHANGE_HANDLER_CLASS,changeHandler.getClass().getName());
  }
  Bundle stateBundle=new Bundle();
  if (changeHandler != null) {
    changeHandler.saveToBundle(stateBundle);
  }
  bundle.putBundle(KEY_HANDLER_STATE,stateBundle);
}
