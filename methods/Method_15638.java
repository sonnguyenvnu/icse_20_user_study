/** 
 * ????????
 * @param configPredicate ??????????
 * @param < T >             ????????
 * @return {@link Optional}
 * @see this#scope(String, String, String)
 */
@SuppressWarnings("all") default <T extends DataAccessConfig>Optional<T> findDataAccess(DataAccessPredicate<T> configPredicate){
  return (Optional)getDataAccesses().stream().filter(configPredicate).findFirst();
}
