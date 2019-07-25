@Override public Iterator<Long> iterator(){
  return new Iterator<Long>(){
    @Override public boolean hasNext(){
      final Iterator<Long> current=getCurrent();
      return current != null && current.hasNext();
    }
    @Override public Long next(){
      final long result=getCurrent().next();
      if (!getCurrent().hasNext()) {
        mCurrent=null;
      }
      return result;
    }
    @Override public void remove(){
      throw new UnsupportedOperationException();
    }
    private Iterator<Long> getCurrent(){
      if (mCurrent != null) {
        return mCurrent;
      }
      if (mIndex < mList.size()) {
        return mCurrent=mList.get(mIndex++).iterator();
      }
      return null;
    }
  }
;
}
