/** 
 * Increases the written counter by the specified value until it reaches Long.MAX_VALUE.
 */
protected void incCount(int value){
  long temp=written + value;
  if (temp < 0) {
    temp=Long.MAX_VALUE;
  }
  written=temp;
}
