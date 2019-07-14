public static int getColorIndex(int id){
  if (id >= 0 && id < 7) {
    return id;
  }
  return Math.abs(id % Theme.keys_avatar_background.length);
}
