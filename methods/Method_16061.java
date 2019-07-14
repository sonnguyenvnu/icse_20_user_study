@SuppressWarnings("all") public static Set<String> include(String... inculdeProperties){
  return new HashSet<String>(Arrays.asList(inculdeProperties)){
    @Override public boolean contains(    Object o){
      return !super.contains(o);
    }
  }
;
}
