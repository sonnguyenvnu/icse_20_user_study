/** 
 * Returns  {@code true} if the layout call mounted the component.
 */
boolean layout(){
  assertMainThread();
  return mountComponentIfNeeded();
}
