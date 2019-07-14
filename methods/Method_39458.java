/** 
 * Extracts props to target map. This is all-in-one method, that does many things at once.
 */
public Map extract(Map target,final String[] profiles,final String[] wildcardPatterns,String prefix){
  if (target == null) {
    target=new HashMap();
  }
  if (prefix != null) {
    if (!StringUtil.endsWithChar(prefix,'.')) {
      prefix+=StringPool.DOT;
    }
  }
  if (profiles != null) {
    for (    String profile : profiles) {
      while (true) {
        final Map<String,PropsEntry> map=this.profileProperties.get(profile);
        if (map != null) {
          extractMap(target,map,profiles,wildcardPatterns,prefix);
        }
        final int ndx=profile.lastIndexOf('.');
        if (ndx == -1) {
          break;
        }
        profile=profile.substring(0,ndx);
      }
    }
  }
  extractMap(target,this.baseProperties,profiles,wildcardPatterns,prefix);
  return target;
}
