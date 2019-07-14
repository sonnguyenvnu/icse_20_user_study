public static boolean isMusic(File file){
  final String REGEX="(.*/)*.+\\.(mp3|m4a|ogg|wav|aac)$";
  return file.getName().matches(REGEX);
}
