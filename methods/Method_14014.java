/** 
 * @return true when this change is empty and its subject is not new
 */
@JsonIgnore public boolean isNull(){
  return isEmpty() && !isNew();
}
