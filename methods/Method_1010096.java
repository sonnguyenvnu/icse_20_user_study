/** 
 * Invoke to set new cached value
 */
final void update(SModel model,DebugInfo cache){
  final SModelReference mr=model.getReference();
  myCache.put(mr,cache);
}
