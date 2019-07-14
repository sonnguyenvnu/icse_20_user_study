public ImmutableMap.Builder prepareScript(String inline){
  final Map<String,String> script=ImmutableMap.of(ES_INLINE_KEY,inline,ES_LANG_KEY,scriptLang());
  return ImmutableMap.builder().put(ES_SCRIPT_KEY,script);
}
