/** 
 * @return a sequence of integers representing the UTF-16 code units from front to back
 */
public PrimitiveIterator.OfInt chars(){
  return IntIterators.flatMap(chunks(),UnicodeChunk::codeUnitIterator);
}
