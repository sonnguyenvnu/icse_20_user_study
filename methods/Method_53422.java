/** 
 * Constructs a Category object from rawJSON string.
 * @param rawJSON raw JSON form as String
 * @return Category
 * @throws TwitterException when provided string is not a valid JSON string.
 * @since Twitter4J 2.1.7
 */
public static Category createCategory(String rawJSON) throws TwitterException {
  try {
    return new CategoryJSONImpl(new JSONObject(rawJSON));
  }
 catch (  JSONException e) {
    throw new TwitterException(e);
  }
}
