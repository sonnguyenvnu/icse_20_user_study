private static Comparator<Media> getTypeComparator(){
  return (f1,f2) -> f1.getMimeType().compareTo(f2.getMimeType());
}
