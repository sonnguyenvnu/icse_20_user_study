/** 
 * Return whether the two maps have the same keys and values. 
 */
public static <K,V>boolean areMapsEqual(@Nullable Map<K,V> prev,@Nullable Map<K,V> next){
  if (prev == next) {
    return true;
  }
  if (prev == null || next == null) {
    return false;
  }
  if (prev.size() != next.size()) {
    return false;
  }
  for (  Map.Entry<K,V> entry : prev.entrySet()) {
    if (!CommonUtils.equals(entry.getValue(),next.get(entry.getKey()))) {
      return false;
    }
  }
  return true;
}
