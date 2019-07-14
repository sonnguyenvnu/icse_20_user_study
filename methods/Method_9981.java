/** 
 * Gets an unwritten character.
 * @return character
 */
public String getUnwrittenCharacter(){
  final String ret=getUnwrittenCharacterRandom();
  if (StringUtils.isNotBlank(ret)) {
    return ret;
  }
  return getUnwrittenCharacterOneByOne();
}
