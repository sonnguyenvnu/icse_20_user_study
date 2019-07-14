public static boolean isRunning(Sprite... sprites){
  for (  Sprite sprite : sprites) {
    if (sprite.isRunning()) {
      return true;
    }
  }
  return false;
}
