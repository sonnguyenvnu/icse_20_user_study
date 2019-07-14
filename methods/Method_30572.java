private void fixTime(){
  if (time.startsWith("\"") && time.endsWith("\"")) {
    time=time.substring(1,time.length() - 1);
  }
}
