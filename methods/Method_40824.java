public String toString(){
  if (location != null) {
    return location.getFileLineCol() + ": " + msg;
  }
 else {
    return msg;
  }
}
