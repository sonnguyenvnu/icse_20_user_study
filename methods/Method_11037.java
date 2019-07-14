/** 
 * ??????????
 */
public void skipTools(){
  if (Build.VERSION.SDK_INT < 19) {
    return;
  }
  getWindow().setFlags(LayoutParams.FLAG_FULLSCREEN,LayoutParams.FLAG_FULLSCREEN);
}
