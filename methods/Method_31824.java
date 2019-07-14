/** 
 * Gets an array of the fields that this partial supports. <p> The fields are returned largest to smallest, for example Hour, Minute, Second.
 * @return the fields supported in an array that may be altered, largest to smallest
 */
public DateTimeField[] getFields(){
  DateTimeField[] result=new DateTimeField[size()];
  for (int i=0; i < result.length; i++) {
    result[i]=getField(i);
  }
  return result;
}
