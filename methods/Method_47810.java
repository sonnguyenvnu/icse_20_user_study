private List<Pair<Field,Column>> getFieldColumnPairs(){
  List<Pair<Field,Column>> fields=new ArrayList<>();
  for (  Field field : klass.getDeclaredFields())   for (  Annotation annotation : field.getAnnotations()) {
    if (!(annotation instanceof Column))     continue;
    Column column=(Column)annotation;
    fields.add(new ImmutablePair<>(field,column));
  }
  return fields;
}
