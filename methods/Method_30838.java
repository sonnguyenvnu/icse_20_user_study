protected boolean persistBoolean(boolean value){
  if (!shouldPersist()) {
    return false;
  }
  if (value == getPersistedBoolean(!value)) {
    return true;
  }
  SharedPreferences.Editor editor=getSharedPreferences().edit();
  editor.putBoolean(mKey,value);
  tryCommit(editor);
  return true;
}
