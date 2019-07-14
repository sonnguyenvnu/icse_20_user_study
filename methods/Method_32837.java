@Override public void onBell(TerminalSession session){
  if (mSessionChangeCallback != null)   mSessionChangeCallback.onBell(session);
}
