/** 
 * Lookups for value converter for current path.
 */
protected ValueConverter lookupValueConverter(){
  if (convs == null) {
    return null;
  }
  return convs.get(path);
}
