/** 
 * Specifies the new index.
 */
public void setIndex(final int newIndex){
  if (newIndex < 0) {
    throw new ArrayIndexOutOfBoundsException(newIndex);
  }
  if (newIndex > array.length) {
    String[] newArray=new String[newIndex];
    System.arraycopy(array,0,newArray,0,index);
    array=newArray;
  }
  if (newIndex > index) {
    for (int i=index; i < newIndex; i++) {
      array[i]=StringPool.EMPTY;
    }
  }
 else   if (newIndex < index) {
    for (int i=newIndex; i < index; i++) {
      array[i]=null;
    }
  }
  index=newIndex;
  length=calculateLength();
}
