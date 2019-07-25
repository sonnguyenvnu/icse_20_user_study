private static long length(Value v){
switch (v.getValueType()) {
case Value.BLOB:
case Value.CLOB:
case Value.BYTES:
case Value.JAVA_OBJECT:
    return v.getType().getPrecision();
default :
  return v.getString().length();
}
}
