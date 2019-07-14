/** 
 * zhongshu-comment scripted_field only allows script(name,script) or script(name,lang,script)
 * @param method
 * @throws SqlParseException
 */
private void handleScriptField(MethodField method) throws SqlParseException {
  List<KVValue> params=method.getParams();
  if (params.size() == 2) {
    String f=params.get(0).value.toString();
    fieldNames.add(f);
    request.addScriptField(f,new Script(params.get(1).value.toString()));
  }
 else   if (params.size() == 3) {
    String f=params.get(0).value.toString();
    fieldNames.add(f);
    request.addScriptField(f,new Script(ScriptType.INLINE,params.get(1).value.toString(),params.get(2).value.toString(),Collections.emptyMap()));
  }
 else {
    throw new SqlParseException("scripted_field only allows script(name,script) or script(name,lang,script)");
  }
}
