/** 
 * Appends single filter as NOT.
 * @param emailFilter {@link EmailFilter} to append.
 * @return this
 */
public EmailFilter not(final EmailFilter emailFilter){
  final SearchTerm searchTerm=new NotTerm(emailFilter.searchTerm);
  concat(searchTerm);
  return this;
}
