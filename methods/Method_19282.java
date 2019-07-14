/** 
 * Inserts a new item at tail. The  {@link RecyclerView} gets notified immediately about thenew item being inserted. If the item's position falls within the currently visible range, the layout is immediately computed on the] UiThread. The RenderInfo contains the component that will be inserted in the Binder and extra info like isSticky or spanCount.
 */
@UiThread public final void appendItem(RenderInfo renderInfo){
  insertItemAt(getItemCount(),renderInfo);
}
