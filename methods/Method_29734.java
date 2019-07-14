/** 
 * Unsupported operation. Due to the limitation of  {@link AccountManager}, we can only access a value by its key, but we cannot access the key set or the entire map.. Calling this method will throw an {@link UnsupportedOperationException}.
 * @throws UnsupportedOperationException
 */
@Override public Map<String,?> getAll(){
  throw new UnsupportedOperationException("getAll() is not supported by AccountManager");
}
