/** 
 * ?????????????(??????),??:???????
 * @param action ?????? {@link Permission#ACTION_QUERY}
 * @return {@link Optional}
 * @see FieldFilterDataAccessConfig
 * @see FieldFilterDataAccessConfig#getFields()
 */
default Optional<FieldFilterDataAccessConfig> findFieldFilter(String action){
  return findDataAccess(conf -> FieldFilterDataAccessConfig.class.isInstance(conf) && conf.getAction().equals(action));
}
