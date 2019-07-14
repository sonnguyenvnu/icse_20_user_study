private AbstractAggregationBuilder scriptedMetric(MethodField field) throws SqlParseException {
  String aggName=gettAggNameFromParamsOrAlias(field);
  ScriptedMetricAggregationBuilder scriptedMetricBuilder=AggregationBuilders.scriptedMetric(aggName);
  Map<String,Object> scriptedMetricParams=field.getParamsAsMap();
  if (!scriptedMetricParams.containsKey("map_script") && !scriptedMetricParams.containsKey("map_script_id") && !scriptedMetricParams.containsKey("map_script_file")) {
    throw new SqlParseException("scripted metric parameters must contain map_script/map_script_id/map_script_file parameter");
  }
  HashMap<String,Object> scriptAdditionalParams=new HashMap<>();
  HashMap<String,Object> reduceScriptAdditionalParams=new HashMap<>();
  for (  Map.Entry<String,Object> param : scriptedMetricParams.entrySet()) {
    String paramValue=param.getValue().toString();
    if (param.getKey().startsWith("@")) {
      if (param.getKey().startsWith("@reduce_")) {
        reduceScriptAdditionalParams.put(param.getKey().replace("@reduce_",""),param.getValue());
      }
 else {
        scriptAdditionalParams.put(param.getKey().replace("@",""),param.getValue());
      }
      continue;
    }
switch (param.getKey().toLowerCase()) {
case "map_script":
      scriptedMetricBuilder.mapScript(new Script(paramValue));
    break;
case "map_script_id":
  scriptedMetricBuilder.mapScript(new Script(ScriptType.STORED,Script.DEFAULT_SCRIPT_LANG,paramValue,new HashMap<String,Object>()));
break;
case "init_script":
scriptedMetricBuilder.initScript(new Script(paramValue));
break;
case "init_script_id":
scriptedMetricBuilder.initScript(new Script(ScriptType.STORED,Script.DEFAULT_SCRIPT_LANG,paramValue,new HashMap<String,Object>()));
break;
case "combine_script":
scriptedMetricBuilder.combineScript(new Script(paramValue));
break;
case "combine_script_id":
scriptedMetricBuilder.combineScript(new Script(ScriptType.STORED,Script.DEFAULT_SCRIPT_LANG,paramValue,new HashMap<String,Object>()));
break;
case "reduce_script":
scriptedMetricBuilder.reduceScript(new Script(ScriptType.INLINE,Script.DEFAULT_SCRIPT_LANG,paramValue,reduceScriptAdditionalParams));
break;
case "reduce_script_id":
scriptedMetricBuilder.reduceScript(new Script(ScriptType.STORED,Script.DEFAULT_SCRIPT_LANG,paramValue,reduceScriptAdditionalParams));
break;
case "alias":
case "nested":
case "reverse_nested":
case "children":
break;
default :
throw new SqlParseException("scripted_metric err or not define field " + param.getKey());
}
}
if (scriptAdditionalParams.size() > 0) {
scriptAdditionalParams.put("_agg",new HashMap<>());
scriptedMetricBuilder.params(scriptAdditionalParams);
}
return scriptedMetricBuilder;
}
