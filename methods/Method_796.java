@SuppressWarnings({"unchecked","rawtypes"}) public static <T>T castToEnum(Object obj,Class<T> clazz,ParserConfig mapping){
  try {
    if (obj instanceof String) {
      String name=(String)obj;
      if (name.length() == 0) {
        return null;
      }
      if (mapping == null) {
        mapping=ParserConfig.getGlobalInstance();
      }
      ObjectDeserializer derializer=mapping.getDeserializer(clazz);
      if (derializer instanceof EnumDeserializer) {
        EnumDeserializer enumDeserializer=(EnumDeserializer)derializer;
        return (T)enumDeserializer.getEnumByHashCode(TypeUtils.fnv1a_64(name));
      }
      return (T)Enum.valueOf((Class<? extends Enum>)clazz,name);
    }
    if (obj instanceof BigDecimal) {
      int ordinal=intValue((BigDecimal)obj);
      Object[] values=clazz.getEnumConstants();
      if (ordinal < values.length) {
        return (T)values[ordinal];
      }
    }
    if (obj instanceof Number) {
      int ordinal=((Number)obj).intValue();
      Object[] values=clazz.getEnumConstants();
      if (ordinal < values.length) {
        return (T)values[ordinal];
      }
    }
  }
 catch (  Exception ex) {
    throw new JSONException("can not cast to : " + clazz.getName(),ex);
  }
  throw new JSONException("can not cast to : " + clazz.getName());
}
