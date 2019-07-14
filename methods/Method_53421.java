/** 
 * Constructs a SavedSearch object from rawJSON string.
 * @param rawJSON raw JSON form as String
 * @return SavedSearch
 * @throws TwitterException when provided string is not a valid JSON string.
 * @since Twitter4J 2.1.7
 */
public static SavedSearch createSavedSearch(String rawJSON) throws TwitterException {
  try {
    return new SavedSearchJSONImpl(new JSONObject(rawJSON));
  }
 catch (  JSONException e) {
    throw new TwitterException(e);
  }
}
