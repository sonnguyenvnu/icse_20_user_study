protected <T>T convert(String value,Class<T> t){
  if (t.isEnum()) {
    if (EnumDict.class.isAssignableFrom(t)) {
      T val=(T)EnumDict.find((Class)t,value).orElse(null);
      if (null != val) {
        return val;
      }
    }
    for (    T enumConstant : t.getEnumConstants()) {
      if (((Enum)enumConstant).name().equalsIgnoreCase(value)) {
        return enumConstant;
      }
    }
  }
  return JSON.parseObject(value,t);
}
