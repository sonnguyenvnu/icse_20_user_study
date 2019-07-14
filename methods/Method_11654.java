private FieldExtractor getAnnotationExtractBy(Class clazz,Field field){
  FieldExtractor fieldExtractor=null;
  ExtractBy extractBy=field.getAnnotation(ExtractBy.class);
  if (extractBy != null) {
    Selector selector=ExtractorUtils.getSelector(extractBy);
    ExtractBy.Source source0=extractBy.source();
    if (extractBy.type() == ExtractBy.Type.JsonPath) {
      source0=RawText;
    }
    FieldExtractor.Source source=null;
switch (source0) {
case RawText:
      source=FieldExtractor.Source.RawText;
    break;
case RawHtml:
  source=FieldExtractor.Source.RawHtml;
break;
case SelectedHtml:
source=FieldExtractor.Source.Html;
break;
default :
source=FieldExtractor.Source.Html;
}
fieldExtractor=new FieldExtractor(field,selector,source,extractBy.notNull(),List.class.isAssignableFrom(field.getType()));
fieldExtractor.setSetterMethod(getSetterMethod(clazz,field));
}
return fieldExtractor;
}
