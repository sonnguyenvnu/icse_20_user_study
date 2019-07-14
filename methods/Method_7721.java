public static boolean hasWallpaperFromTheme(){
  return currentColors.containsKey(key_chat_wallpaper) || themedWallpaperFileOffset > 0;
}
