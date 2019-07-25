@Override public Collection<Centroid> centroids(){
  compress();
  return new AbstractCollection<Centroid>(){
    @Override public Iterator<Centroid> iterator(){
      return new Iterator<Centroid>(){
        @Override public boolean hasNext(){
          return i < lastUsedCell;
        }
        @Override public Centroid next(){
          Centroid rc=new Centroid(mean[i],(int)weight[i],data != null ? data.get(i) : null);
          i++;
          return rc;
        }
        @Override public void remove(){
          throw new UnsupportedOperationException("Default operation");
        }
      }
;
    }
    @Override public int size(){
      return lastUsedCell;
    }
  }
;
}
