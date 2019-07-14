private static void match(MethodInvocationTree method,String methodName,ListMultimap<ProtoField,FieldWithValue> setters){
  for (  FieldType fieldType : FieldType.values()) {
    FieldWithValue match=fieldType.match(methodName,method);
    if (match != null) {
      setters.put(match.getField(),match);
    }
  }
}
