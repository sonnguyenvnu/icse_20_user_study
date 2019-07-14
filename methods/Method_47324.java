public static float readableFileSizeFloat(long size){
  if (size <= 0)   return 0;
  return (float)(size / (1024 * 1024));
}
