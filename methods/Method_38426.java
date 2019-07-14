/** 
 * Check if decora tag is currently defined and throws an exception on nested tags.
 */
protected void checkNestedDecoraTags(){
  if (decoraTagName != null) {
    throw new DecoraException("Nested Decora tags not allowed");
  }
}
