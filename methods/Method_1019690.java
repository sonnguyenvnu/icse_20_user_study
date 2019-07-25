public void load(FieldsMetadata fieldsMetadata,String key,Class<?> clazz,boolean listType) throws XDocReportException {
  try {
    List<PropertyDescriptor> path=new ArrayList<PropertyDescriptor>();
    process(fieldsMetadata,key,clazz,path,listType);
  }
 catch (  Exception e) {
    throw new XDocReportException(e);
  }
}
