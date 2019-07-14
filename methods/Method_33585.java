/** 
 * ??????????Header??
 */
private void scrollChangeHeader(int scrolledY){
  if (scrolledY < 0) {
    scrolledY=0;
  }
  if (scrolledY < slidingDistance) {
    StatusBarUtils.setTranslucentImageHeader(this,scrolledY * 50 / slidingDistance,rlHead);
    rlHead.setBackgroundColor(Color.argb(scrolledY * 50 / slidingDistance,0x00,0x00,0x00));
    llHeader.setPadding(0,-scrolledY,0,0);
    currScrollY=scrolledY;
  }
 else {
    StatusBarUtils.setTranslucentImageHeader(this,50,rlHead);
    rlHead.setBackgroundColor(Color.argb(50,0x00,0x00,0x00));
    llHeader.setPadding(0,-slidingDistance,0,0);
    currScrollY=slidingDistance;
  }
}
