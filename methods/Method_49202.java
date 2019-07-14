@Override public Iterator<InternalVertex> iterator(){
  return new Iterator<InternalVertex>(){
    private InternalVertex nextVertex(){
      InternalVertex v=null;
      while (v == null && iterator.hasNext()) {
        final long nextId=iterator.next();
        if (IDManager.VertexIDType.Invisible.is(nextId))         continue;
        v=tx.getInternalVertex(nextId);
        if (v.isRemoved())         v=null;
      }
      return v;
    }
    @Override public boolean hasNext(){
      return nextVertex != null;
    }
    @Override public InternalVertex next(){
      if (!hasNext())       throw new NoSuchElementException();
      final InternalVertex returnVertex=nextVertex;
      nextVertex=nextVertex();
      return returnVertex;
    }
    @Override public void remove(){
      throw new UnsupportedOperationException();
    }
  }
;
}
