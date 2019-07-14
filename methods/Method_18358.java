/** 
 * Sets whether the next mount will be the first mount of this ComponentTree. This is used to determine whether to run animations or not (we want animations to run on the first mount of this ComponentTree, but not other times the mounted ComponentTree id changes). Ideally, we want animations to only occur when the ComponentTree is updated on screen or is first inserted into a list onscreen, but that requires more integration with the list controller, e.g. sections, than we currently have.
 */
void setIsFirstMountOfComponentTree(){
  assertMainThread();
  mIsFirstMountOfComponentTree=true;
}
