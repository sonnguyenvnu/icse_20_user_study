/** 
 * Add this setting to the builder if it doesn't exists in the source settings. The value added to the builder is taken from the given default settings object.
 * @param builder the settings builder to fill the diff into
 * @param source the source settings object to diff
 * @param defaultSettings the default settings object to diff against
 */
public void diff(Settings.Builder builder,Settings source,Settings defaultSettings){
  if (exists(source) == false) {
    builder.put(getKey(),getRaw(defaultSettings));
  }
}
