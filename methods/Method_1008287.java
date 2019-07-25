@Override public final BytesRefIterator iterator(){
  final int offset=this.offset;
  final int length=this.length;
  final int initialFragmentSize=offset != 0 ? PAGE_SIZE - (offset % PAGE_SIZE) : PAGE_SIZE;
  return new BytesRefIterator(){
    @Override public BytesRef next() throws IOException {
      if (nextFragmentSize != 0) {
        final boolean materialized=byteArray.get(offset + position,nextFragmentSize,slice);
        assert materialized == false : "iteration should be page aligned but array got materialized";
        position+=nextFragmentSize;
        final int remaining=length - position;
        nextFragmentSize=Math.min(remaining,PAGE_SIZE);
        return slice;
      }
 else {
        assert nextFragmentSize == 0 : "fragmentSize expected [0] but was: [" + nextFragmentSize + "]";
        return null;
      }
    }
  }
;
}
