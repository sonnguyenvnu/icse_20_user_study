/** 
 * Returns <code>true</code> if resource link  should be collected into the bundle. Returns <code>false</code> for resources that has to ignored or when no link existed (<code>null</code>). <p> By default, ignores resource links that contains "jodd.unstaple" (usually set as dummy parameter name).
 */
public boolean acceptLink(final String src){
  if (src == null) {
    return false;
  }
  return !src.contains(UNSTAPLE_MARKER);
}
