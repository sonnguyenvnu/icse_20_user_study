/** 
 * Convert a Struct to an ImmutableMap. 
 */
public static ImmutableMap<String,Object> toMap(Struct struct){
  return struct.getFieldsMap().entrySet().stream().collect(ImmutableMap.toImmutableMap(entry -> entry.getKey(),entry -> {
    Value value=entry.getValue();
switch (value.getKindCase()) {
case STRUCT_VALUE:
      return toMap(value.getStructValue());
case LIST_VALUE:
    return toList(value.getListValue());
default :
  return getScalarValue(value);
}
}
));
}
