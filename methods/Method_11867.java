private static Comparator<Method> getSorter(FixMethodOrder fixMethodOrder){
  if (fixMethodOrder == null) {
    return DEFAULT;
  }
  return fixMethodOrder.value().getComparator();
}
