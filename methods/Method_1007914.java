private static int size(){
  int i=0;
  while (true) {
    String val=MDC.get(PREFIX + i);
    if (val != null) {
      i++;
    }
 else {
      break;
    }
  }
  return i;
}
