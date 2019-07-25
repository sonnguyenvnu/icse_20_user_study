/** 
 * Factory method for constructing  {@link ObjectReader} that willuse specified character escaping details for output.
 */
public ObjectWriter writer(CharacterEscapes escapes){
  return _newWriter(serializationConfig()).with(escapes);
}
