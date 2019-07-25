/** 
 * Recycles the Renderer getting it from the tag associated to the renderer root view. This view is not used with RecyclerView widget.
 * @param convertView that contains the tag.
 * @param content to be updated in the recycled renderer.
 * @return a recycled renderer.
 */
private Renderer recycle(View convertView,T content){
  Renderer renderer=(Renderer)convertView.getTag();
  renderer.onRecycle(content);
  return renderer;
}
