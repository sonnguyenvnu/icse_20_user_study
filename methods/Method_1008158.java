/** 
 * Parses restore definition
 * @param source restore definition
 * @return this request
 */
@SuppressWarnings("unchecked") public RestoreSnapshotRequest source(Map<String,Object> source){
  for (  Map.Entry<String,Object> entry : source.entrySet()) {
    String name=entry.getKey();
    if (name.equals("indices")) {
      if (entry.getValue() instanceof String) {
        indices(Strings.splitStringByCommaToArray((String)entry.getValue()));
      }
 else       if (entry.getValue() instanceof ArrayList) {
        indices((ArrayList<String>)entry.getValue());
      }
 else {
        throw new IllegalArgumentException("malformed indices section, should be an array of strings");
      }
    }
 else     if (name.equals("partial")) {
      partial(nodeBooleanValue(entry.getValue(),"partial"));
    }
 else     if (name.equals("settings")) {
      if (!(entry.getValue() instanceof Map)) {
        throw new IllegalArgumentException("malformed settings section");
      }
      settings((Map<String,Object>)entry.getValue());
    }
 else     if (name.equals("include_global_state")) {
      includeGlobalState=nodeBooleanValue(entry.getValue(),"include_global_state");
    }
 else     if (name.equals("include_aliases")) {
      includeAliases=nodeBooleanValue(entry.getValue(),"include_aliases");
    }
 else     if (name.equals("rename_pattern")) {
      if (entry.getValue() instanceof String) {
        renamePattern((String)entry.getValue());
      }
 else {
        throw new IllegalArgumentException("malformed rename_pattern");
      }
    }
 else     if (name.equals("rename_replacement")) {
      if (entry.getValue() instanceof String) {
        renameReplacement((String)entry.getValue());
      }
 else {
        throw new IllegalArgumentException("malformed rename_replacement");
      }
    }
 else     if (name.equals("index_settings")) {
      if (!(entry.getValue() instanceof Map)) {
        throw new IllegalArgumentException("malformed index_settings section");
      }
      indexSettings((Map<String,Object>)entry.getValue());
    }
 else     if (name.equals("ignore_index_settings")) {
      if (entry.getValue() instanceof String) {
        ignoreIndexSettings(Strings.splitStringByCommaToArray((String)entry.getValue()));
      }
 else       if (entry.getValue() instanceof List) {
        ignoreIndexSettings((List<String>)entry.getValue());
      }
 else {
        throw new IllegalArgumentException("malformed ignore_index_settings section, should be an array of strings");
      }
    }
 else {
      if (IndicesOptions.isIndicesOptions(name) == false) {
        throw new IllegalArgumentException("Unknown parameter " + name);
      }
    }
  }
  indicesOptions(IndicesOptions.fromMap(source,indicesOptions));
  return this;
}
