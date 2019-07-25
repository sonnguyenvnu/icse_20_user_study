/** 
 * ????
 * @return is collide
 */
protected boolean collide(float x,float y){
  int offset=(int)(rangeSeekBar.getProgressWidth() * currPercent);
  return x > left + offset && x < right + offset && y > top && y < bottom;
}
