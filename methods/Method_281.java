private static @Nullable Unbinder parseBindArray(Object target,Field field,View source){
  BindArray bindArray=field.getAnnotation(BindArray.class);
  if (bindArray == null) {
    return null;
  }
  validateMember(field);
  int id=bindArray.value();
  Resources resources=source.getContext().getResources();
  Object value;
  Class<?> fieldType=field.getType();
  if (fieldType == TypedArray.class) {
    value=resources.obtainTypedArray(id);
  }
 else   if (fieldType.isArray()) {
    Class<?> componentType=fieldType.getComponentType();
    if (componentType == String.class) {
      value=resources.getStringArray(id);
    }
 else     if (componentType == int.class) {
      value=resources.getIntArray(id);
    }
 else     if (componentType == CharSequence.class) {
      value=resources.getTextArray(id);
    }
 else {
      throw new IllegalStateException("@BindArray field type must be one of: " + "String[], int[], CharSequence[], android.content.res.TypedArray. (" + field.getDeclaringClass().getName() + '.' + field.getName() + ')');
    }
  }
 else {
    throw new IllegalStateException("@BindArray field type must be one of: " + "String[], int[], CharSequence[], android.content.res.TypedArray. (" + field.getDeclaringClass().getName() + '.' + field.getName() + ')');
  }
  trySet(field,target,value);
  return Unbinder.EMPTY;
}
