private void init(Class clazz){
  this.clazz=clazz;
  initClassExtractors();
  fieldExtractors=new ArrayList<FieldExtractor>();
  for (  Field field : ClassUtils.getFieldsIncludeSuperClass(clazz)) {
    field.setAccessible(true);
    FieldExtractor fieldExtractor=getAnnotationExtractBy(clazz,field);
    FieldExtractor fieldExtractorTmp=getAnnotationExtractCombo(clazz,field);
    if (fieldExtractor != null && fieldExtractorTmp != null) {
      throw new IllegalStateException("Only one of 'ExtractBy ComboExtract ExtractByUrl' can be added to a field!");
    }
 else     if (fieldExtractor == null && fieldExtractorTmp != null) {
      fieldExtractor=fieldExtractorTmp;
    }
    fieldExtractorTmp=getAnnotationExtractByUrl(clazz,field);
    if (fieldExtractor != null && fieldExtractorTmp != null) {
      throw new IllegalStateException("Only one of 'ExtractBy ComboExtract ExtractByUrl' can be added to a field!");
    }
 else     if (fieldExtractor == null && fieldExtractorTmp != null) {
      fieldExtractor=fieldExtractorTmp;
    }
    if (fieldExtractor != null) {
      fieldExtractor.setObjectFormatter(new ObjectFormatterBuilder().setField(field).build());
      fieldExtractors.add(fieldExtractor);
    }
  }
}
