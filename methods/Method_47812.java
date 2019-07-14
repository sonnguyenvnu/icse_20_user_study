@NonNull private Table getTableAnnotation(){
  Table t=null;
  for (  Annotation annotation : klass.getAnnotations()) {
    if (!(annotation instanceof Table))     continue;
    t=(Table)annotation;
    break;
  }
  if (t == null)   throw new RuntimeException("Table annotation not found");
  return t;
}
