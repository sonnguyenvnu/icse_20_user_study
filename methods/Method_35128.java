@Override public boolean removesFromViewOnPush(){
  if (changeHandler != null) {
    return changeHandler.removesFromViewOnPush();
  }
  return true;
}
