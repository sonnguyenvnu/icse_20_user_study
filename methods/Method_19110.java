private static Map<Class<? extends Annotation>,DelegateMethodDescription> getTreeMap(){
  return new TreeMap<>(new Comparator<Class<? extends Annotation>>(){
    @Override public int compare(    Class<? extends Annotation> lhs,    Class<? extends Annotation> rhs){
      return lhs.toString().compareTo(rhs.toString());
    }
  }
);
}
