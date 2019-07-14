/** 
 * Parse original data with type  {@link O} into model data with type {@link Card}
 * @param data Original data.
 * @return Parsed data.
 * @since 3.0.0
 */
public Card parseSingleData(@Nullable O data){
  return mDataParser.parseSingleGroup(data,this);
}
