public void setContext(SerialContext parent,Object object,Object fieldName,int features,int fieldFeatures){
  if (out.disableCircularReferenceDetect) {
    return;
  }
  this.context=new SerialContext(parent,object,fieldName,features,fieldFeatures);
  if (references == null) {
    references=new IdentityHashMap<Object,SerialContext>();
  }
  this.references.put(object,context);
}
