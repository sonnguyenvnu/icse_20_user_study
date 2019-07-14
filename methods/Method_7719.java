public static void setAnimatedColor(String key,int value){
  if (animatingColors == null) {
    return;
  }
  animatingColors.put(key,value);
}
