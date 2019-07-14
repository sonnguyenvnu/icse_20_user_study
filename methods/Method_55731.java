public static boolean areCapabilitiesDifferent(PointerBuffer ref,PointerBuffer caps){
  for (int i=0; i < ref.remaining(); i++) {
    if (ref.get(i) != caps.get(i) && caps.get(i) != NULL) {
      return true;
    }
  }
  return false;
}
