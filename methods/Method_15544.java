private Object getValue(@NotNull Object value){
  if (isPrepared()) {
    preparedValueList.add(value);
    return "?";
  }
  return (value instanceof Number || value instanceof Boolean) ? value : "'" + value + "'";
}
