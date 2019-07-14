protected Map<Class,HeaderMapper> createHeaderMapping(Class type){
  if (type == String.class || Number.class.isAssignableFrom(type) || ClassUtils.isPrimitiveWrapper(type) || type.isPrimitive() || type.isEnum() || type.isArray() || Date.class.isAssignableFrom(type)) {
    return new java.util.HashMap<>();
  }
  AtomicInteger index=new AtomicInteger(0);
  Map<Class,DefaultHeaderMapper> headerMapperMap=new HashMap<>();
  ReflectionUtils.doWithFields(type,field -> {
    Excel excel=field.getAnnotation(Excel.class);
    ApiModelProperty apiModelProperty=field.getAnnotation(ApiModelProperty.class);
    if ((excel == null && apiModelProperty == null) || (excel != null && excel.ignore())) {
      return;
    }
    String header=excel == null ? apiModelProperty.value() : excel.value();
    HeaderMapping mapping=new HeaderMapping();
    mapping.header=header;
    mapping.field=field.getName();
    mapping.index=excel == null || excel.exportOrder() == -1 ? index.getAndAdd(1) : excel.exportOrder();
    if (null != excel) {
      mapping.enableImport=excel.enableImport();
      mapping.enableExport=excel.enableExport();
      mapping.children=() -> getHeaderMapper(field.getType(),excel.group());
      for (      Class group : excel.group()) {
        headerMapperMap.computeIfAbsent(group,DefaultHeaderMapper::new).mappings.add(mapping);
      }
      mapping.converter=createConvert(excel.converter(),field.getType());
    }
 else {
      mapping.converter=createConvert(ExcelCellConverter.class,field.getType());
      mapping.children=() -> getHeaderMapper(field.getType());
      headerMapperMap.computeIfAbsent(Void.class,DefaultHeaderMapper::new).mappings.add(mapping);
    }
  }
);
  return (Map)headerMapperMap;
}
