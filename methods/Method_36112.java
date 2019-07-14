/** 
 * @param context Servlet context for parameter reading
 * @return Maximum number of entries or absent
 */
private Optional<Integer> readMaxRequestJournalEntries(ServletContext context){
  String str=context.getInitParameter("maxRequestJournalEntries");
  if (str == null) {
    return Optional.absent();
  }
  return Optional.of(Integer.parseInt(str));
}
