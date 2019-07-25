/** 
 * <p>This creates a large object, returning its OID.</p> <p>It defaults to READWRITE for the new object's attributes.</p>
 * @return oid of new object
 * @throws SQLException on error
 * @deprecated As of 8.3, replaced by {@link #createLO()}
 */
@Deprecated public int create() throws SQLException {
  return create(READWRITE);
}
