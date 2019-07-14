/** 
 * Reads property value and  {@link #onSerializableProperty(String,Class,Object) serializes it}.
 */
@Override protected final void onSerializableProperty(String propertyName,final PropertyDescriptor propertyDescriptor){
  final Object value;
  if (propertyDescriptor == null) {
    value=source.getClass().getName();
  }
 else {
    value=readProperty(source,propertyDescriptor);
    if ((value == null) && jsonContext.isExcludeNulls()) {
      return;
    }
    propertyName=typeData.resolveJsonName(propertyName);
  }
  onSerializableProperty(propertyName,propertyDescriptor == null ? null : propertyDescriptor.getType(),value);
}
