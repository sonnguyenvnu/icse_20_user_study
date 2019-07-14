/** 
 * See  {@link RecyclerBinder#appendItem(RenderInfo)}.
 */
@UiThread public final void appendItem(Component component){
  insertItemAt(getItemCount(),component);
}
