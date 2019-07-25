/** 
 * Returns a new settings object that contains all setting of the current one filtered by the given settings key predicate.
 */
public Settings filter(Predicate<String> predicate){
  return new Settings(new FilteredMap(this.settings,predicate,null),secureSettings == null ? null : new PrefixedSecureSettings(secureSettings,"",predicate));
}
