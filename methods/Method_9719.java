/** 
 * Gets a character by the specified character content in the specified characters.
 * @param content    the specified character content
 * @param characters the specified characters
 * @return character, returns {@code null} if not found
 */
public static JSONObject getCharacter(final String content,final Set<JSONObject> characters){
  for (  final JSONObject character : characters) {
    if (character.optString(CHARACTER_CONTENT).equals(content)) {
      return character;
    }
  }
  return null;
}
