/** 
 * Gets all expressions from the preference store
 * @return
 */
@JsonIgnore public List<String> getExpressions(){
  return ((TopList)_preferenceStore.get("scripting.expressions")).getList();
}
