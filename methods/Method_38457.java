/** 
 * Ensures that last buffer exist.
 */
private void ensureLast(){
  if (last == null) {
    last=new FastByteBuffer();
    list.add(last);
  }
}
