/** 
 * Adds a setter and/or getter method to the property. If property is already defined, the new, updated, definition will be created.
 */
protected void addProperty(final HashMap<String,PropertyDescriptor> map,final String name,final MethodDescriptor methodDescriptor,final boolean isSetter){
  MethodDescriptor setterMethod=isSetter ? methodDescriptor : null;
  MethodDescriptor getterMethod=isSetter ? null : methodDescriptor;
  PropertyDescriptor existing=map.get(name);
  if (existing == null) {
    PropertyDescriptor propertyDescriptor=createPropertyDescriptor(name,getterMethod,setterMethod);
    map.put(name,propertyDescriptor);
    return;
  }
  if (!isSetter) {
    setterMethod=existing.getWriteMethodDescriptor();
    MethodDescriptor existingMethodDescriptor=existing.getReadMethodDescriptor();
    if (existingMethodDescriptor != null) {
      String methodName=methodDescriptor.getMethod().getName();
      String existingMethodName=existingMethodDescriptor.getMethod().getName();
      if (existingMethodName.startsWith(METHOD_IS_PREFIX) && methodName.startsWith(METHOD_GET_PREFIX)) {
        return;
      }
    }
  }
 else {
    getterMethod=existing.getReadMethodDescriptor();
    if (getterMethod != null) {
      Class returnType=getterMethod.getMethod().getReturnType();
      if (setterMethod != null) {
        Class parameterType=setterMethod.getMethod().getParameterTypes()[0];
        if (returnType != parameterType) {
          return;
        }
      }
    }
  }
  PropertyDescriptor propertyDescriptor=createPropertyDescriptor(name,getterMethod,setterMethod);
  map.put(name,propertyDescriptor);
}
