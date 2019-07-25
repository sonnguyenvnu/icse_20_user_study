default <T>T convert(Class<T> cls,String key,T defaultValue){
  String value=(String)getProperty(key);
  if (value == null) {
    return defaultValue;
  }
  Object obj=value;
  if (cls.isInstance(value)) {
    return cls.cast(value);
  }
  if (String.class.equals(cls)) {
    return cls.cast(value);
  }
  if (Boolean.class.equals(cls) || Boolean.TYPE.equals(cls)) {
    obj=Boolean.valueOf(value);
  }
 else   if (Number.class.isAssignableFrom(cls) || cls.isPrimitive()) {
    if (Integer.class.equals(cls) || Integer.TYPE.equals(cls)) {
      obj=Integer.valueOf(value);
    }
 else     if (Long.class.equals(cls) || Long.TYPE.equals(cls)) {
      obj=Long.valueOf(value);
    }
 else     if (Byte.class.equals(cls) || Byte.TYPE.equals(cls)) {
      obj=Byte.valueOf(value);
    }
 else     if (Short.class.equals(cls) || Short.TYPE.equals(cls)) {
      obj=Short.valueOf(value);
    }
 else     if (Float.class.equals(cls) || Float.TYPE.equals(cls)) {
      obj=Float.valueOf(value);
    }
 else     if (Double.class.equals(cls) || Double.TYPE.equals(cls)) {
      obj=Double.valueOf(value);
    }
  }
 else   if (cls.isEnum()) {
    obj=Enum.valueOf(cls.asSubclass(Enum.class),value);
  }
  return cls.cast(obj);
}
