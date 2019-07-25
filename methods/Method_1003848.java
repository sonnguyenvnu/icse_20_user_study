/** 
 * Like  {@link #get(String,SessionSerializer)}, but throws  {@link NoSuchElementException} on the absence of a value.<p> This method will throw an  {@link IllegalArgumentException} if there is more than one object who's key has the given name.
 * @param name the object name
 * @param serializer the serializer
 * @return the value for the given key
 */
default Object require(String name,SessionSerializer serializer) throws Exception {
  return require(SessionKey.of(name),serializer);
}
