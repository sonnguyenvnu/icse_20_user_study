public static int getMinutes(long time){
  return (int)((time / ONE_MINUTE) % 60);
}
