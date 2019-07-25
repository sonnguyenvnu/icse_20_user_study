@Override public Value cast(Value value){
switch (value.getValueType()) {
case Value.ENUM:
    if (value instanceof ValueEnum && ((ValueEnum)value).getEnumerators().equals(this)) {
      return value;
    }
case Value.STRING:
case Value.STRING_FIXED:
case Value.STRING_IGNORECASE:
  ValueEnum v=getValueOrNull(value.getString());
if (v != null) {
  return v;
}
break;
default :
int ordinal=value.getInt();
if (ordinal >= 0 && ordinal < enumerators.length) {
return new ValueEnum(this,enumerators[ordinal],ordinal);
}
}
String s=value.getTraceSQL();
if (s.length() > 127) {
s=s.substring(0,128) + "...";
}
throw DbException.get(ErrorCode.ENUM_VALUE_NOT_PERMITTED,toString(),s);
}
