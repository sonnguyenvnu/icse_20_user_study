/** 
 * The first call of this method lazily sets its child to the referenced subtree created through the {@link BehaviorTreeLibraryManager}. Subsequent calls do nothing since the child has already been set. A {@link UnsupportedOperationException} is thrown if this {@code Include} is eager.
 * @throws UnsupportedOperationException if this {@code Include} is eager 
 */
@Override public void start(){
  if (!lazy)   throw new UnsupportedOperationException("A non-lazy " + Include.class.getSimpleName() + " isn't meant to be run!");
  if (child == null) {
    addChild(createSubtreeRootTask());
  }
}
