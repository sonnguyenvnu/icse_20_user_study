/** 
 * See  {@link RecyclerBinder#insertItemAt(int,RenderInfo)}.
 */
@UiThread public final void insertItemAt(int position,Component component){
  insertItemAt(position,ComponentRenderInfo.create().component(component).build());
}
