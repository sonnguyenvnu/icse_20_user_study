private String getDeletionScript(KeyInformation.IndexRetriever information,String storeName,IndexMutation mutation) throws PermanentBackendException {
  final StringBuilder script=new StringBuilder();
  final String INDEX_NAME="index";
  int i=0;
  for (  final IndexEntry deletion : mutation.getDeletions()) {
    final KeyInformation keyInformation=information.get(storeName).get(deletion.field);
switch (keyInformation.getCardinality()) {
case SINGLE:
      script.append("ctx._source.remove(\"").append(deletion.field).append("\");");
    if (hasDualStringMapping(information.get(storeName,deletion.field))) {
      script.append("ctx._source.remove(\"").append(getDualMappingName(deletion.field)).append("\");");
    }
  break;
case SET:
case LIST:
final String jsValue=convertToJsType(deletion.value,compat.scriptLang(),Mapping.getMapping(keyInformation));
String index=INDEX_NAME + i++;
script.append("def ").append(index).append(" = ctx._source[\"").append(deletion.field).append("\"].indexOf(").append(jsValue).append("); ctx._source[\"").append(deletion.field).append("\"].remove(").append(index).append(");");
if (hasDualStringMapping(information.get(storeName,deletion.field))) {
index=INDEX_NAME + i++;
script.append("def ").append(index).append(" = ctx._source[\"").append(getDualMappingName(deletion.field)).append("\"].indexOf(").append(jsValue).append("); ctx._source[\"").append(getDualMappingName(deletion.field)).append("\"].remove(").append(index).append(");");
}
break;
}
}
return script.toString();
}
