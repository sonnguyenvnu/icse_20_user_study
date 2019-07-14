/** 
 * Returns the number of elements of the Handler list that begins with the given element.
 * @param firstHandler the beginning of a Handler list. May be {@literal null}.
 * @return the number of elements of the Handler list that begins with 'handler'.
 */
static int getExceptionTableLength(final Handler firstHandler){
  int length=0;
  Handler handler=firstHandler;
  while (handler != null) {
    length++;
    handler=handler.nextHandler;
  }
  return length;
}
