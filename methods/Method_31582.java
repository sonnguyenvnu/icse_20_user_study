/** 
 * Configures this reader for placeholder replacement.
 * @param r The original reader.
 * @return The new reader with placeholder replacement.
 */
protected Reader replacePlaceholders(Reader r){
  if (configuration.isPlaceholderReplacement()) {
    return new PlaceholderReplacingReader(configuration.getPlaceholderPrefix(),configuration.getPlaceholderSuffix(),configuration.getPlaceholders(),r);
  }
  return r;
}
