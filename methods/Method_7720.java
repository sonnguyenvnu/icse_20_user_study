public static void setColor(String key,int color,boolean useDefault){
  if (key.equals(key_chat_wallpaper)) {
    color=0xff000000 | color;
  }
  if (useDefault) {
    currentColors.remove(key);
  }
 else {
    currentColors.put(key,color);
  }
  if (key.equals(key_chat_serviceBackground) || key.equals(key_chat_serviceBackgroundSelected)) {
    applyChatServiceMessageColor();
  }
 else   if (key.equals(key_chat_wallpaper)) {
    reloadWallpaper();
  }
}
