private static int getSideCoord(boolean isX,int side,float p,int sideSize){
  int total;
  if (isX) {
    total=AndroidUtilities.displaySize.x - sideSize;
  }
 else {
    total=AndroidUtilities.displaySize.y - sideSize - ActionBar.getCurrentActionBarHeight();
  }
  int result;
  if (side == 0) {
    result=AndroidUtilities.dp(10);
  }
 else   if (side == 1) {
    result=total - AndroidUtilities.dp(10);
  }
 else {
    result=Math.round((total - AndroidUtilities.dp(20)) * p) + AndroidUtilities.dp(10);
  }
  if (!isX) {
    result+=ActionBar.getCurrentActionBarHeight();
  }
  return result;
}
