@SuppressWarnings("unchecked") private static Property<View,PointF> getChangeBoundsProperty(String fieldName){
  Object fieldValue=ReflectionUtils.getFieldValue(null,null,ReflectionUtils.getPrivateField(android.transition.ChangeBounds.class,fieldName));
  if (fieldValue instanceof Property) {
    Property<View,PointF> property=(Property<View,PointF>)fieldValue;
    try {
      property.set(null,new PointF());
    }
 catch (    NullPointerException e) {
    }
catch (    Exception e) {
      property=null;
    }
    return property;
  }
 else {
    return null;
  }
}
