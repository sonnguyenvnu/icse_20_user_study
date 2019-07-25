/** 
 * Parse list of Data objects. The  {@link DataSchema}'s parsed are in  {@link #topLevelDataSchemas}. Parse errors are in  {@link #errorMessageBuilder} and indicatedby  {@link #hasError()}.
 * @param list of Data objects.
 */
public void parse(List<Object> list){
  for (  Object o : list) {
    DataSchema schema=parseObject(o);
    if (schema != null) {
      addTopLevelSchema(schema);
    }
  }
}
