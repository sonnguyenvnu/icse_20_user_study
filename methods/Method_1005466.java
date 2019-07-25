public List<CustomConverterDescription> build(BeanContainer beanContainer){
  List<CustomConverterDescription> answer=new ArrayList<>();
  if (converter != null && converter.size() > 0) {
    for (    ConverterTypeDefinition current : converter) {
      answer.add(current.build(beanContainer));
    }
  }
  return answer;
}
