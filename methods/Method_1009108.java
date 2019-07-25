/** 
 * Not supported.
 * @param uri    This parameter is ignored.
 * @param name  The name of the attribute.
 * @param value The value of the attribute.
 * @throws UnsupportedOperationException This class does not handle namespaces.
 */
@Override public void attribute(String uri,String name,int value) throws UnsupportedOperationException {
  throw new UnsupportedOperationException("This class does not handle namespaces");
}
