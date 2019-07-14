/** 
 * Returns a  {@link AIVector2D} view of the {@code mTranslation} field. 
 */
@NativeType("struct aiVector2D") public AIVector2D mTranslation(){
  return nmTranslation(address());
}
