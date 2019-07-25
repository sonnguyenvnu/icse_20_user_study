public static boolean contains(String[] allowedEvents,String event){
  for (  String e : allowedEvents) {
    if (e.equalsIgnoreCase(event)) {
      return true;
    }
  }
  return false;
}
