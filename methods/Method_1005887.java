/** 
 * Creates a new Java object pointing at the same V8 Value as this. If the value is mutated (by adding new members or changing existing ones) then both the original and twin will be updated. Twins are .equal and .strict equals, but not == in Java. Twins must be closed separately since they have their own native resources.
 * @return A new Java object pointing at the same V8 Valueas this.
 */
public V8Value twin(){
  if (isUndefined()) {
    return this;
  }
  v8.checkThread();
  v8.checkReleased();
  V8Value twin=createTwin();
  v8.createTwin(this,twin);
  return twin;
}
