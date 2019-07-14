/** 
 * Rolls up SQL exceptions by taking each proceeding exception and making it a child of the previous using the <code>setNextException</code> method of SQLException.
 */
public static SQLException rollupSqlExceptions(final Collection<SQLException> exceptions){
  SQLException parent=null;
  for (  SQLException exception : exceptions) {
    if (parent != null) {
      exception.setNextException(parent);
    }
    parent=exception;
  }
  return parent;
}
