@SuppressWarnings({"rawtypes","unchecked"}) public void arrayAdd(Object rootObject,Object... values){
  if (values == null || values.length == 0) {
    return;
  }
  if (rootObject == null) {
    return;
  }
  init();
  Object currentObject=rootObject;
  Object parentObject=null;
  for (int i=0; i < segments.length; ++i) {
    if (i == segments.length - 1) {
      parentObject=currentObject;
    }
    currentObject=segments[i].eval(this,rootObject,currentObject);
  }
  Object result=currentObject;
  if (result == null) {
    throw new JSONPathException("value not found in path " + path);
  }
  if (result instanceof Collection) {
    Collection collection=(Collection)result;
    for (    Object value : values) {
      collection.add(value);
    }
    return;
  }
  Class<?> resultClass=result.getClass();
  Object newResult;
  if (resultClass.isArray()) {
    int length=Array.getLength(result);
    Object descArray=Array.newInstance(resultClass.getComponentType(),length + values.length);
    System.arraycopy(result,0,descArray,0,length);
    for (int i=0; i < values.length; ++i) {
      Array.set(descArray,length + i,values[i]);
    }
    newResult=descArray;
  }
 else {
    throw new JSONException("unsupported array put operation. " + resultClass);
  }
  Segment lastSegment=segments[segments.length - 1];
  if (lastSegment instanceof PropertySegment) {
    PropertySegment propertySegment=(PropertySegment)lastSegment;
    propertySegment.setValue(this,parentObject,newResult);
    return;
  }
  if (lastSegment instanceof ArrayAccessSegment) {
    ((ArrayAccessSegment)lastSegment).setValue(this,parentObject,newResult);
    return;
  }
  throw new UnsupportedOperationException();
}
