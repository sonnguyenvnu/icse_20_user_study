private String pad(String in){
  if (in.length() == 0) {
    return "00";
  }
  if (in.length() == 1) {
    return "0" + in;
  }
  return in;
}
