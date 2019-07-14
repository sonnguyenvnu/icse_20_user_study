protected Map<Class,Validator> createValidator(Class target){
  Map<String,LogicPrimaryKey> keys=new HashMap<>();
  ReflectionUtils.doWithFields(target,field -> {
    LogicPrimaryKey primaryKey=field.getAnnotation(LogicPrimaryKey.class);
    if (primaryKey != null) {
      keys.put(field.getName(),primaryKey);
    }
  }
);
  Class[] tempClass=new Class[]{target};
  LogicPrimaryKey classAnn=null;
  Class[] empty=new Class[0];
  while (tempClass.length != 0) {
    for (    Class group : tempClass) {
      classAnn=AnnotationUtils.findAnnotation(group,LogicPrimaryKey.class);
      if (null != classAnn) {
        if (classAnn.value().length > 0) {
          for (          String field : classAnn.value()) {
            keys.put(field,classAnn);
          }
          tempClass=empty;
          continue;
        }
 else {
          tempClass=classAnn.groups();
          if (tempClass.length == 1 && tempClass[0] == Void.class) {
            log.warn("?{}???{}??,???value????group??",classAnn,tempClass);
            continue;
          }
        }
      }
 else {
        tempClass=empty;
        continue;
      }
    }
  }
  if (keys.isEmpty()) {
    return new java.util.HashMap<>();
  }
  return keys.entrySet().stream().flatMap(entry -> Stream.of(entry.getValue().groups()).flatMap(group -> Optional.ofNullable(entry.getValue().value()).map(Arrays::asList).filter(CollectionUtils::isNotEmpty).orElse(Arrays.asList(entry.getKey())).stream().map(field -> LogicPrimaryKeyField.builder().field(field).termType(entry.getValue().termType()).condition(entry.getValue().condition()).matchNullOrEmpty(entry.getValue().matchNullOrEmpty()).group(group).build()))).collect(Collectors.groupingBy(LogicPrimaryKeyField::getGroup,Collectors.collectingAndThen(Collectors.mapping(Function.identity(),Collectors.toSet()),list -> DefaultValidator.builder().infos(list).targetType(target).build())));
}
