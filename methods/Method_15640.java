/** 
 * ???????????
 * @param action ????
 * @return ???????set, ????null
 */
default Set<String> findDenyFields(String action){
  return findFieldFilter(action).filter(conf -> DENY_FIELDS.equals(conf.getType())).map(FieldFilterDataAccessConfig::getFields).orElseGet(Collections::emptySet);
}
