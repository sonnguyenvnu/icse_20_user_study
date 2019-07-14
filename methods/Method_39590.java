/** 
 * Removes the range between start and end from the Handler list that begins with the given element.
 * @param firstHandler the beginning of a Handler list. May be {@literal null}.
 * @param start the start of the range to be removed.
 * @param end the end of the range to be removed. Maybe {@literal null}.
 * @return the exception handler list with the start-end range removed.
 */
static Handler removeRange(final Handler firstHandler,final Label start,final Label end){
  if (firstHandler == null) {
    return null;
  }
 else {
    firstHandler.nextHandler=removeRange(firstHandler.nextHandler,start,end);
  }
  int handlerStart=firstHandler.startPc.bytecodeOffset;
  int handlerEnd=firstHandler.endPc.bytecodeOffset;
  int rangeStart=start.bytecodeOffset;
  int rangeEnd=end == null ? Integer.MAX_VALUE : end.bytecodeOffset;
  if (rangeStart >= handlerEnd || rangeEnd <= handlerStart) {
    return firstHandler;
  }
  if (rangeStart <= handlerStart) {
    if (rangeEnd >= handlerEnd) {
      return firstHandler.nextHandler;
    }
 else {
      return new Handler(firstHandler,end,firstHandler.endPc);
    }
  }
 else   if (rangeEnd >= handlerEnd) {
    return new Handler(firstHandler,firstHandler.startPc,start);
  }
 else {
    firstHandler.nextHandler=new Handler(firstHandler,end,firstHandler.endPc);
    return new Handler(firstHandler,firstHandler.startPc,start);
  }
}
