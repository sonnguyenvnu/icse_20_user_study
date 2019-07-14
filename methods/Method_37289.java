/** 
 * Returns all fields of this collection. Returns empty array if no fields exist. Initialized lazy.
 */
public FieldDescriptor[] getAllFieldDescriptors(){
  if (allFields == null) {
    FieldDescriptor[] allFields=new FieldDescriptor[fieldsMap.size()];
    int index=0;
    for (    FieldDescriptor fieldDescriptor : fieldsMap.values()) {
      allFields[index]=fieldDescriptor;
      index++;
    }
    Arrays.sort(allFields,Comparator.comparing(fd -> fd.getField().getName()));
    this.allFields=allFields;
  }
  return allFields;
}
