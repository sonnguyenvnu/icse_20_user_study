private FieldExtractor getAnnotationExtractCombo(Class clazz,Field field){
  FieldExtractor fieldExtractor=null;
  ComboExtract comboExtract=field.getAnnotation(ComboExtract.class);
  if (comboExtract != null) {
    ExtractBy[] extractBies=comboExtract.value();
    Selector selector;
switch (comboExtract.op()) {
case And:
      selector=new AndSelector(ExtractorUtils.getSelectors(extractBies));
    break;
case Or:
  selector=new OrSelector(ExtractorUtils.getSelectors(extractBies));
break;
default :
selector=new AndSelector(ExtractorUtils.getSelectors(extractBies));
}
fieldExtractor=new FieldExtractor(field,selector,comboExtract.source() == ComboExtract.Source.RawHtml ? FieldExtractor.Source.RawHtml : FieldExtractor.Source.Html,comboExtract.notNull(),comboExtract.multi() || List.class.isAssignableFrom(field.getType()));
Method setterMethod=getSetterMethod(clazz,field);
if (setterMethod != null) {
fieldExtractor.setSetterMethod(setterMethod);
}
}
return fieldExtractor;
}
