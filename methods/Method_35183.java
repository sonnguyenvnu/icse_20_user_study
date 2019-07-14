private static boolean containedBeforeIndex(@NonNull List<View> views,View view,int maxIndex){
  for (int i=0; i < maxIndex; i++) {
    if (views.get(i) == view) {
      return true;
    }
  }
  return false;
}
