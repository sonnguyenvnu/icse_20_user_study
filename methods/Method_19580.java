public static int getHours(long time){
  return (int)((time / ONE_HOUR) % 12);
}
