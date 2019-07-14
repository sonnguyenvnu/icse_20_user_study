public void handleResovleTask(Object value){
  if (resolveTaskList == null) {
    return;
  }
  for (int i=0, size=resolveTaskList.size(); i < size; ++i) {
    ResolveTask task=resolveTaskList.get(i);
    String ref=task.referenceValue;
    Object object=null;
    if (task.ownerContext != null) {
      object=task.ownerContext.object;
    }
    Object refValue;
    if (ref.startsWith("$")) {
      refValue=getObject(ref);
      if (refValue == null) {
        try {
          refValue=JSONPath.eval(value,ref);
        }
 catch (        JSONPathException ex) {
        }
      }
    }
 else {
      refValue=task.context.object;
    }
    FieldDeserializer fieldDeser=task.fieldDeserializer;
    if (fieldDeser != null) {
      if (refValue != null && refValue.getClass() == JSONObject.class && fieldDeser.fieldInfo != null && !Map.class.isAssignableFrom(fieldDeser.fieldInfo.fieldClass)) {
        Object root=this.contextArray[0].object;
        refValue=JSONPath.eval(root,ref);
      }
      fieldDeser.setValue(object,refValue);
    }
  }
}
