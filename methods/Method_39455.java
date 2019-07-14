/** 
 * Counts profile properties. Note: this method is not that easy on execution.
 */
public int countProfileProperties(){
  final HashSet<String> profileKeys=new HashSet<>();
  for (  final Map<String,PropsEntry> map : profileProperties.values()) {
    for (    final String key : map.keySet()) {
      if (!baseProperties.containsKey(key)) {
        profileKeys.add(key);
      }
    }
  }
  return profileKeys.size();
}
