/** 
 * ????
 * @param upToDown
 */
private void scrollPage(boolean upToDown){
  Log.d(TAG,"scrollPage upToDown = " + upToDown + "; lv.getHeight() = " + lv.getHeight());
  lv.smoothScrollBy((int)((upToDown ? -0.8 : 0.8) * lv.getHeight()),120);
}
