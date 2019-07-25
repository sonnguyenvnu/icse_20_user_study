/** 
 * Increases the capacity of the array and copies the contents of the old into the new array
 */
public static Header[] resize(final Header[] headers){
  int new_capacity=headers.length + RESIZE_INCR;
  Header[] new_hdrs=new Header[new_capacity];
  System.arraycopy(headers,0,new_hdrs,0,headers.length);
  return new_hdrs;
}
