@Override public void onTextChanged(TerminalSession changedSession){
  if (mSessionChangeCallback != null)   mSessionChangeCallback.onTextChanged(changedSession);
}
