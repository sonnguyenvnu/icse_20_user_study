/** 
 * Scans class for annotations and returns  {@link TypeData}.
 */
private TypeData scanClassForAnnotations(final Class type){
  ClassDescriptor cd=ClassIntrospector.get().lookup(type);
  PropertyDescriptor[] pds=cd.getAllPropertyDescriptors();
  ArrayList<String> includedList=new ArrayList<>();
  ArrayList<String> excludedList=new ArrayList<>();
  ArrayList<String> jsonNames=new ArrayList<>();
  ArrayList<String> realNames=new ArrayList<>();
  AnnotationParser annotationParser=JSONAnnotationValues.parserFor(jsonAnnotation);
  for (  PropertyDescriptor pd : pds) {
    JSONAnnotationValues data=null;
{
      MethodDescriptor md=pd.getReadMethodDescriptor();
      if (md != null) {
        Method method=md.getMethod();
        data=JSONAnnotationValues.of(annotationParser,method);
      }
    }
    if (data == null) {
      MethodDescriptor md=pd.getWriteMethodDescriptor();
      if (md != null) {
        Method method=md.getMethod();
        data=JSONAnnotationValues.of(annotationParser,method);
      }
    }
    if (data == null) {
      FieldDescriptor fd=pd.getFieldDescriptor();
      if (fd != null) {
        Field field=fd.getField();
        data=JSONAnnotationValues.of(annotationParser,field);
      }
    }
    if (data != null) {
      String propertyName=pd.getName();
      String newPropertyName=data.name();
      if (newPropertyName != null) {
        realNames.add(propertyName);
        jsonNames.add(newPropertyName);
        propertyName=newPropertyName;
      }
      if (data.include()) {
        includedList.add(propertyName);
      }
 else {
        excludedList.add(propertyName);
      }
    }
  }
  String[] reals=null;
  if (!realNames.isEmpty()) {
    reals=realNames.toArray(new String[0]);
  }
  String[] jsons=null;
  if (!jsonNames.isEmpty()) {
    jsons=jsonNames.toArray(new String[0]);
  }
  JSONAnnotationValues data=JSONAnnotationValues.of(annotationParser,type);
  return new TypeData(includedList,excludedList,data != null && data.strict(),jsons,reals);
}
