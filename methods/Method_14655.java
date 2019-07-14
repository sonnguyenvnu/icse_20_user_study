/** 
 * Gets all the project tags currently held in memory
 * @return
 */
@JsonIgnore public Map<String,Integer> getAllProjectTags(){
  return _projectsTags;
}
