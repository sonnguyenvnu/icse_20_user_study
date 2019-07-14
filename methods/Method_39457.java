/** 
 * Lookup props value through profiles and base properties. Returns  {@code null} if value not found.
 */
protected String lookupValue(final String key,final String... profiles){
  if (profiles != null) {
    for (    String profile : profiles) {
      if (profile == null) {
        continue;
      }
      while (true) {
        final Map<String,PropsEntry> profileMap=this.profileProperties.get(profile);
        if (profileMap != null) {
          final PropsEntry value=profileMap.get(key);
          if (value != null) {
            return value.getValue(profiles);
          }
        }
        final int ndx=profile.lastIndexOf('.');
        if (ndx == -1) {
          break;
        }
        profile=profile.substring(0,ndx);
      }
    }
  }
  final PropsEntry value=getBaseProperty(key);
  if (value == null) {
    return null;
  }
  return value.getValue(profiles);
}
