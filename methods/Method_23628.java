/** 
 * Resize the internal data, this can only be used to shrink the list. Helpful for situations like sorting and then grabbing the top 50 entries.
 */
public void resize(int length){
  if (length > count) {
    throw new IllegalArgumentException("resize() can only be used to shrink the dictionary");
  }
  if (length < 1) {
    throw new IllegalArgumentException("resize(" + length + ") is too small, use 1 or higher");
  }
  String[] newKeys=new String[length];
  int[] newValues=new int[length];
  PApplet.arrayCopy(keys,newKeys,length);
  PApplet.arrayCopy(values,newValues,length);
  keys=newKeys;
  values=newValues;
  count=length;
  resetIndices();
}
