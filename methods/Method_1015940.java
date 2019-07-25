/** 
 * Determines whether the specified json object can not be persisted (add or update) into an repository which specified by the given repository name. <p> A valid json object to persist must match keys definitions (including type and length if had) in the repository description (repository.json) with the json object names itself. </p> <p> The specified keys to ignore will be bypassed, regardless of matching keys definitions. </p>
 * @param repositoryName the given repository name (maybe with table name prefix)
 * @param jsonObject     the specified json object
 * @param ignoredKeys    the specified keys to ignore
 * @throws RepositoryException if the specified json object can not be persisted
 * @see Repository#add(org.json.JSONObject)
 * @see Repository#update(java.lang.String,org.json.JSONObject)
 */
public static void check(final String repositoryName,final JSONObject jsonObject,final String... ignoredKeys) throws RepositoryException {
  if (null == jsonObject) {
    throw new RepositoryException("Null to persist to repository [" + repositoryName + "]");
  }
  final JSONObject repositoryDef=getRepositoryDef(repositoryName);
  if (!repositoryDef.optBoolean("fieldcheck")) {
    return;
  }
  final boolean needIgnoreKeys=null != ignoredKeys && 0 < ignoredKeys.length;
  final JSONArray names=jsonObject.names();
  final Set<Object> nameSet=CollectionUtils.jsonArrayToSet(names);
  final JSONArray keysDef=repositoryDef.optJSONArray("keys");
  if (null == keysDef) {
    return;
  }
  final Set<String> keySet=new HashSet<>();
  for (int i=0; i < keysDef.length(); i++) {
    final JSONObject keyDescription=keysDef.optJSONObject(i);
    final String key=keyDescription.optString("name");
    keySet.add(key);
    if (needIgnoreKeys && Strings.containsIgnoreCase(key,ignoredKeys)) {
      continue;
    }
    if (!keyDescription.optBoolean("nullable") && !nameSet.contains(key)) {
      throw new RepositoryException("A json object to persist to repository [name=" + repositoryName + "] does not contain a key [" + key + "]");
    }
  }
  for (int i=0; i < names.length(); i++) {
    final String name=names.optString(i);
    if (!keySet.contains(name)) {
      throw new RepositoryException("A json object to persist to repository [name=" + repositoryName + "] contains an redundant key [" + name + "]");
    }
  }
}
