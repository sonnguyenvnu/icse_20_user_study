public String convert(MappingContext<Object,String> context){
  Object source=context.getSource();
  if (source == null)   return null;
  Class<?> sourceType=context.getSourceType();
  return sourceType.isArray() && sourceType.getComponentType() == Character.TYPE || sourceType.getComponentType() == Character.class ? String.valueOf((char[])source) : source.toString();
}
