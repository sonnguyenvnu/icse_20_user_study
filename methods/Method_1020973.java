/** 
 * Apply the rules map from a JSON. It will replace the original map.
 * @param targetMapJson The target map JSON of your rules.
 * @throws JSONException When the JSON's format is error or unexpected.
 */
public static void apply(@NonNull final String targetMapJson) throws JSONException {
  configuration().apply(new TargetMapParser().fromJson(targetMapJson));
}
