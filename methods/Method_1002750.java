/** 
 * Finds the  {@link SerializationFormat} with the specified {@link #uriText()}.
 */
public static Optional<SerializationFormat> find(String uriText){
  uriText=Ascii.toLowerCase(requireNonNull(uriText,"uriText"));
  return Optional.ofNullable(uriTextToFormats.get(uriText));
}
