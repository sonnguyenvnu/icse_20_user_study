static ValueSource wrap(Object value,ScriptService scriptService){
  if (value instanceof Map) {
    @SuppressWarnings("unchecked") Map<Object,Object> mapValue=(Map)value;
    Map<ValueSource,ValueSource> valueTypeMap=new HashMap<>(mapValue.size());
    for (    Map.Entry<Object,Object> entry : mapValue.entrySet()) {
      valueTypeMap.put(wrap(entry.getKey(),scriptService),wrap(entry.getValue(),scriptService));
    }
    return new MapValue(valueTypeMap);
  }
 else   if (value instanceof List) {
    @SuppressWarnings("unchecked") List<Object> listValue=(List)value;
    List<ValueSource> valueSourceList=new ArrayList<>(listValue.size());
    for (    Object item : listValue) {
      valueSourceList.add(wrap(item,scriptService));
    }
    return new ListValue(valueSourceList);
  }
 else   if (value == null || value instanceof Number || value instanceof Boolean) {
    return new ObjectValue(value);
  }
 else   if (value instanceof byte[]) {
    return new ByteValue((byte[])value);
  }
 else   if (value instanceof String) {
    if (scriptService.isLangSupported(DEFAULT_TEMPLATE_LANG)) {
      Script script=new Script(ScriptType.INLINE,DEFAULT_TEMPLATE_LANG,(String)value,Collections.emptyMap());
      return new TemplatedValue(scriptService.compile(script,TemplateScript.CONTEXT));
    }
 else {
      return new ObjectValue(value);
    }
  }
 else {
    throw new IllegalArgumentException("unexpected value type [" + value.getClass() + "]");
  }
}
