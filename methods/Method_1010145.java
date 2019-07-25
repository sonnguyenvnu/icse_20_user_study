@Override public void info(String message){
  if (!myHandleInfo) {
    return;
  }
  report(MessageKind.INFORMATION,message,null);
}
