public static void start(Music music,Context context){
  context.startService(makeIntent(music,context));
}
