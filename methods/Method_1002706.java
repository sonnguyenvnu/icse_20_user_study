/** 
 * Returns the path of this request. This method is a shortcut of  {@code headers().path()}.
 */
default String path(){
  return headers().path();
}
