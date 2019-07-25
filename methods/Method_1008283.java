/** 
 * Returns a BytesRefIterator for this BytesReference. This method allows access to the internal pages of this reference without copying them. Use with care!
 * @see BytesRefIterator
 */
public BytesRefIterator iterator(){
  return new BytesRefIterator(){
    @Override public BytesRef next() throws IOException {
      BytesRef r=ref;
      ref=null;
      return r;
    }
  }
;
}
