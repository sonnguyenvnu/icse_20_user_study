public static Field[] getSupportedFields(final Class clazz,final Class limit){
  final ArrayList<Field> supportedFields=new ArrayList<>();
  for (Class c=clazz; c != limit && c != null; c=c.getSuperclass()) {
    final Field[] fields=c.getDeclaredFields();
    for (    final Field field : fields) {
      boolean found=false;
      for (      final Field supportedField : supportedFields) {
        if (compareSignatures(field,supportedField)) {
          found=true;
          break;
        }
      }
      if (!found) {
        supportedFields.add(field);
      }
    }
  }
  return supportedFields.toArray(new Field[0]);
}
