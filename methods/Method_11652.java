private FieldExtractor getAnnotationExtractByUrl(Class clazz,Field field){
  FieldExtractor fieldExtractor=null;
  ExtractByUrl extractByUrl=field.getAnnotation(ExtractByUrl.class);
  if (extractByUrl != null) {
    String regexPattern=extractByUrl.value();
    if (regexPattern.trim().equals("")) {
      regexPattern=".*";
    }
    fieldExtractor=new FieldExtractor(field,new RegexSelector(regexPattern),FieldExtractor.Source.Url,extractByUrl.notNull(),extractByUrl.multi() || List.class.isAssignableFrom(field.getType()));
    Method setterMethod=getSetterMethod(clazz,field);
    if (setterMethod != null) {
      fieldExtractor.setSetterMethod(setterMethod);
    }
  }
  return fieldExtractor;
}
