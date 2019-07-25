@Override public BytesRefIterator iterator(){
  if (references.length > 0) {
    return new BytesRefIterator(){
      @Override public BytesRef next() throws IOException {
        BytesRef next=current.next();
        if (next == null) {
          while (index < references.length) {
            current=references[index++].iterator();
            next=current.next();
            if (next != null) {
              break;
            }
          }
        }
        return next;
      }
    }
;
  }
 else {
    return () -> null;
  }
}
