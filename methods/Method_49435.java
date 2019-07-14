private String convertToEsDataType(Class<?> dataType,Mapping mapping){
  if (String.class.isAssignableFrom(dataType)) {
    return "string";
  }
 else   if (Integer.class.isAssignableFrom(dataType)) {
    return "integer";
  }
 else   if (Long.class.isAssignableFrom(dataType)) {
    return "long";
  }
 else   if (Float.class.isAssignableFrom(dataType)) {
    return "float";
  }
 else   if (Double.class.isAssignableFrom(dataType)) {
    return "double";
  }
 else   if (Boolean.class.isAssignableFrom(dataType)) {
    return "boolean";
  }
 else   if (Date.class.isAssignableFrom(dataType)) {
    return "date";
  }
 else   if (Instant.class.isAssignableFrom(dataType)) {
    return "date";
  }
 else   if (Geoshape.class.isAssignableFrom(dataType)) {
    return mapping == Mapping.DEFAULT ? "geo_point" : "geo_shape";
  }
  return null;
}
