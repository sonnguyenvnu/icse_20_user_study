/** 
 * @param path the path to find
 * @return the id of the longest containing MenuMetadata.path in getDrawerMetadata() or null
 */
public @Nullable Integer findLongestContainingDrawerItem(CharSequence path){
  return tree.getValueForLongestKeyPrefixing(path);
}
