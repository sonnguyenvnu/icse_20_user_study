private static int getSize(float size){
  return (int)(size < 0 ? size : AndroidUtilities.dp(size));
}
