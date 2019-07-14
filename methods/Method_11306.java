/** 
 * Compare the size of two floating point numbers
 * @param a
 * @param b
 * @return 1 is a > b-1 is a < b 0 is a == b
 */
private int compareFloat(float a,float b){
  int ta=Math.round(a * 1000);
  int tb=Math.round(b * 1000);
  if (ta > tb) {
    return 1;
  }
 else   if (ta < tb) {
    return -1;
  }
 else {
    return 0;
  }
}
