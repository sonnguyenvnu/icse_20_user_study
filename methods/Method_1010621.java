private void log(String text,Level level){
  if (!(level.isGreaterOrEqual(myWhatToDo.getLogLevel()))) {
    return;
  }
  if (level == Level.ERROR) {
    System.err.println(text);
  }
 else {
    System.out.println(text);
  }
}
