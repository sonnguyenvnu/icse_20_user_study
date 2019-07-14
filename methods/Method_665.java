public final void writeWithFieldName(Object object,Object fieldName,Type fieldType,int fieldFeatures){
  try {
    if (object == null) {
      out.writeNull();
      return;
    }
    Class<?> clazz=object.getClass();
    ObjectSerializer writer=getObjectWriter(clazz);
    writer.write(this,object,fieldName,fieldType,fieldFeatures);
  }
 catch (  IOException e) {
    throw new JSONException(e.getMessage(),e);
  }
}
