public Object createInstance(DefaultJSONParser parser,Type type){
  if (type instanceof Class) {
    if (clazz.isInterface()) {
      Class<?> clazz=(Class<?>)type;
      ClassLoader loader=Thread.currentThread().getContextClassLoader();
      final JSONObject obj=new JSONObject();
      Object proxy=Proxy.newProxyInstance(loader,new Class<?>[]{clazz},obj);
      return proxy;
    }
  }
  if (beanInfo.defaultConstructor == null && beanInfo.factoryMethod == null) {
    return null;
  }
  if (beanInfo.factoryMethod != null && beanInfo.defaultConstructorParameterSize > 0) {
    return null;
  }
  Object object;
  try {
    Constructor<?> constructor=beanInfo.defaultConstructor;
    if (beanInfo.defaultConstructorParameterSize == 0) {
      if (constructor != null) {
        object=constructor.newInstance();
      }
 else {
        object=beanInfo.factoryMethod.invoke(null);
      }
    }
 else {
      ParseContext context=parser.getContext();
      if (context == null || context.object == null) {
        throw new JSONException("can't create non-static inner class instance.");
      }
      final String typeName;
      if (type instanceof Class) {
        typeName=((Class<?>)type).getName();
      }
 else {
        throw new JSONException("can't create non-static inner class instance.");
      }
      final int lastIndex=typeName.lastIndexOf('$');
      String parentClassName=typeName.substring(0,lastIndex);
      Object ctxObj=context.object;
      String parentName=ctxObj.getClass().getName();
      Object param=null;
      if (!parentName.equals(parentClassName)) {
        ParseContext parentContext=context.parent;
        if (parentContext != null && parentContext.object != null && ("java.util.ArrayList".equals(parentName) || "java.util.List".equals(parentName) || "java.util.Collection".equals(parentName) || "java.util.Map".equals(parentName) || "java.util.HashMap".equals(parentName))) {
          parentName=parentContext.object.getClass().getName();
          if (parentName.equals(parentClassName)) {
            param=parentContext.object;
          }
        }
 else {
          param=ctxObj;
        }
      }
 else {
        param=ctxObj;
      }
      if (param == null || param instanceof Collection && ((Collection)param).isEmpty()) {
        throw new JSONException("can't create non-static inner class instance.");
      }
      object=constructor.newInstance(param);
    }
  }
 catch (  JSONException e) {
    throw e;
  }
catch (  Exception e) {
    throw new JSONException("create instance error, class " + clazz.getName(),e);
  }
  if (parser != null && parser.lexer.isEnabled(Feature.InitStringFieldAsEmpty)) {
    for (    FieldInfo fieldInfo : beanInfo.fields) {
      if (fieldInfo.fieldClass == String.class) {
        try {
          fieldInfo.set(object,"");
        }
 catch (        Exception e) {
          throw new JSONException("create instance error, class " + clazz.getName(),e);
        }
      }
    }
  }
  return object;
}
