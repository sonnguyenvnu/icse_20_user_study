@Override public Iterable<String> list(){
  return new Iterable<String>(){
    @Override public Iterator<String> iterator(){
      return new Iterator<String>(){
        @Override public boolean hasNext(){
          while (next == null && delegate.hasMoreElements()) {
            next=delegate.nextElement();
            if (next.isDirectory()) {
              next=null;
            }
          }
          return next != null;
        }
        @Override public String next(){
          if (hasNext()) {
            String name=next.getName();
            next=null;
            return name;
          }
 else {
            throw new NoSuchElementException();
          }
        }
        @Override public void remove(){
          throw new UnsupportedOperationException();
        }
      }
;
    }
  }
;
}
