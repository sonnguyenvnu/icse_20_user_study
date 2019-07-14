private static void addFieldIfNotExist(final List<Field> allFields,final Field newField){
  for (  Field f : allFields) {
    if (compareSignatures(f,newField)) {
      return;
    }
  }
  allFields.add(newField);
}
