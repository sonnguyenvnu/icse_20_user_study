/** 
 * Returns <code>true</code> iff this setting is present in the given settings object. Otherwise <code>false</code>
 */
public boolean exists(Settings settings){
  return settings.keySet().contains(getKey());
}
