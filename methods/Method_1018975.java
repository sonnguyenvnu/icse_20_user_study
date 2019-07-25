/** 
 * Check if a particular renderer item is still available. <p> Renderer items can come and go as they are discovered or disconnected (or otherwise become unavailable)
 * @param containsItem
 * @return
 */
public boolean contains(RendererItem containsItem){
  for (  RendererItem rendererItem : rendererItems) {
    if (rendererItem.rendererItemInstance().equals(containsItem.rendererItemInstance())) {
      return true;
    }
  }
  return false;
}
