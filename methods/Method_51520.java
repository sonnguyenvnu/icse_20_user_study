/** 
 * Returns an iterator of the reported configuration errors.
 * @return the iterator
 */
public Iterator<ConfigurationError> configErrors(){
  return configErrors == null ? EmptyIterator.<ConfigurationError>instance() : configErrors.iterator();
}
