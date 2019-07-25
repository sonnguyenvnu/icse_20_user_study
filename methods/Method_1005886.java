@Override protected void initialize(final long runtimePtr,final Object data){
  v8.checkThread();
  if (data == null) {
    super.initialize(runtimePtr,data);
    return;
  }
  V8ArrayData arrayData=(V8ArrayData)data;
  checkArrayProperties(arrayData);
  long handle=createTypedArray(runtimePtr,arrayData);
  released=false;
  addObjectReference(handle);
}
