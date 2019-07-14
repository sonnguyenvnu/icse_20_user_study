/** 
 * See  {@link RecyclerBinder#updateItemAt(int,Component)}.
 */
@UiThread public final void updateItemAt(int position,Component component){
  updateItemAt(position,ComponentRenderInfo.create().component(component).build());
}
