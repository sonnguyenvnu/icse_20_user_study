@Override public String getMessage(){
  if (mErrnoMessage == null) {
    return reason.getFormattedDescription();
  }
  return reason.getFormattedDescription() + ": " + mErrnoMessage;
}
