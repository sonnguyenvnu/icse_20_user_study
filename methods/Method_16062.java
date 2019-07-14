private static Map<String,ClassProperty> createProperty(Class type){
  List<String> fieldNames=Arrays.stream(type.getDeclaredFields()).map(Field::getName).collect(Collectors.toList());
  return Stream.of(propertyUtils.getPropertyDescriptors(type)).filter(property -> !property.getName().equals("class") && property.getReadMethod() != null && property.getWriteMethod() != null).map(BeanClassProperty::new).sorted(Comparator.comparing(property -> fieldNames.indexOf(property.name))).collect(Collectors.toMap(ClassProperty::getName,Function.identity(),(k,k2) -> k,LinkedHashMap::new));
}
