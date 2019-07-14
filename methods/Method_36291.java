@Override public void prefetch(Uri uri){
  downloadImageInto(uri,new PrefetchTarget());
}
