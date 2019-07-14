@NonNull private Object createCustom(@NonNull AttributeSet attrs,@NonNull Class expectedType,@NonNull String tag){
  String className=attrs.getAttributeValue(null,"class");
  if (className == null) {
    throw new InflateException(tag + " tag must have a 'class' attribute");
  }
  try {
synchronized (sConstructors) {
      Constructor constructor=sConstructors.get(className);
      if (constructor == null) {
        Class c=mContext.getClassLoader().loadClass(className).asSubclass(expectedType);
        if (c != null) {
          constructor=c.getConstructor(sConstructorSignature);
          if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
          }
          sConstructors.put(className,constructor);
        }
      }
      return constructor.newInstance(mContext,attrs);
    }
  }
 catch (  InstantiationException e) {
    throw new InflateException("Could not instantiate " + expectedType + " class " + className,e);
  }
catch (  ClassNotFoundException e) {
    throw new InflateException("Could not instantiate " + expectedType + " class " + className,e);
  }
catch (  InvocationTargetException e) {
    throw new InflateException("Could not instantiate " + expectedType + " class " + className,e);
  }
catch (  NoSuchMethodException e) {
    throw new InflateException("Could not instantiate " + expectedType + " class " + className,e);
  }
catch (  IllegalAccessException e) {
    throw new InflateException("Could not instantiate " + expectedType + " class " + className,e);
  }
}
