/** 
 * Encodes an Object using the NYSIIS algorithm. This method is provided in order to satisfy the requirements of the Encoder interface, and will throw an  {@link EncoderException} if the supplied object is not of type{@link String}.
 * @param obj Object to encode
 * @return An object (or a {@link String}) containing the NYSIIS code which corresponds to the given String.
 * @throws EncoderException if the parameter supplied is not of a {@link String}
 * @throws IllegalArgumentException if a character is not mapped
 */
@Override public Object encode(Object obj) throws EncoderException {
  if (!(obj instanceof String)) {
    throw new EncoderException("Parameter supplied to Nysiis encode is not of type java.lang.String");
  }
  return this.nysiis((String)obj);
}
