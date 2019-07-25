public void trace(String message){
  for (  String s : message.split("\n")) {
    report(MessageKind.INFORMATION,s,null);
  }
}
