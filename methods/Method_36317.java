public static <T,U>LinkedHashMap<T,U> sortMapAccordingToValue(Map<T,U> origin,BeanFactory beanFactory){
  Comparator<Object> comparatorToUse=null;
  if (beanFactory instanceof DefaultListableBeanFactory) {
    comparatorToUse=((DefaultListableBeanFactory)beanFactory).getDependencyComparator();
  }
  if (comparatorToUse == null) {
    comparatorToUse=OrderComparator.INSTANCE;
  }
  final Comparator<Object> finalComparator=comparatorToUse;
  List<Map.Entry<T,U>> entryList=new ArrayList<>(origin.entrySet());
  Collections.sort(entryList,(o1,o2) -> finalComparator.compare(o1.getValue(),o2.getValue()));
  LinkedHashMap<T,U> result=new LinkedHashMap<>();
  for (  Map.Entry<T,U> entry : entryList) {
    result.put(entry.getKey(),entry.getValue());
  }
  return result;
}
