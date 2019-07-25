final void copy(final LzStore source){
  size=source.size;
  System.arraycopy(source.litLens,0,litLens,0,size);
  System.arraycopy(source.dists,0,dists,0,size);
}
