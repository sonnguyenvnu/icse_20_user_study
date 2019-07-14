private static Comparator<Album> getReversedPinned(){
  return (o1,o2) -> getPinned().compare(o2,o1);
}
