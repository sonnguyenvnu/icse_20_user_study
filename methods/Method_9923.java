/** 
 * Determines whether the specified tag title exists in the specified tags.
 * @param tagTitle the specified tag title
 * @param tags     the specified tags
 * @return {@code true} if it exists, {@code false} otherwise
 * @throws JSONException json exception
 */
private static boolean tagExists(final String tagTitle,final List<JSONObject> tags) throws JSONException {
  for (  final JSONObject tag : tags) {
    if (tag.getString(Tag.TAG_TITLE).equals(tagTitle)) {
      return true;
    }
  }
  return false;
}
