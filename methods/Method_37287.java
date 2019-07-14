/** 
 * Inspects fields and returns map of  {@link FieldDescriptor field descriptors}.
 */
private Map<String,FieldDescriptor> inspectFields(){
  if (classDescriptor.isSystemClass()) {
    return emptyFields();
  }
  final boolean scanAccessible=classDescriptor.isScanAccessible();
  final Class type=classDescriptor.getType();
  final Field[] fields=scanAccessible ? ClassUtil.getAccessibleFields(type) : ClassUtil.getSupportedFields(type);
  final HashMap<String,FieldDescriptor> map=new HashMap<>(fields.length);
  for (  final Field field : fields) {
    final String fieldName=field.getName();
    if (fieldName.equals("serialVersionUID")) {
      continue;
    }
    map.put(fieldName,createFieldDescriptor(field));
  }
  return map;
}
