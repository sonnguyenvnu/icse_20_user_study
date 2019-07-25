/** 
 * Returns a settings object that contains all settings that are not already set in the given source. The diff contains either the default value for each setting or the settings value in the given default settings.
 */
public Settings diff(Settings source,Settings defaultSettings){
  Settings.Builder builder=Settings.builder();
  for (  Setting<?> setting : keySettings.values()) {
    setting.diff(builder,source,defaultSettings);
  }
  for (  Setting<?> setting : complexMatchers.values()) {
    setting.diff(builder,source,defaultSettings);
  }
  return builder.build();
}
