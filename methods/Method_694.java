/** 
 * Returns a copy of the input data.
 * @return an array of chars copied from the input data.
 */
public char[] toCharArray(){
  if (this.writer != null) {
    throw new UnsupportedOperationException("writer not null");
  }
  char[] newValue=new char[count];
  System.arraycopy(buf,0,newValue,0,count);
  return newValue;
}
