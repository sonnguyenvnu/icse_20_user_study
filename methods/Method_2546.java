/** 
 * ??
 * @param keyset
 * @param dawgBuilder
 */
private void buildDawg(Keyset keyset,DawgBuilder dawgBuilder){
  dawgBuilder.init();
  for (int i=0; i < keyset.numKeys(); ++i) {
    dawgBuilder.insert(keyset.getKey(i),keyset.getValue(i));
  }
  dawgBuilder.finish();
}
