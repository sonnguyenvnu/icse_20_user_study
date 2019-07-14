/** 
 * Called during DataDiffSection's diffing to determine if two objects represent the same item. See  {@link androidx.recyclerview.widget.DiffUtil.Callback#areItemsTheSame(int,int)} for moreinfo.
 * @return true if the two objects in the event represent the same item.
 */
@OnEvent(OnCheckIsSameItemEvent.class) static boolean isSameItem(ComponentContext c,@FromEvent DemoListActivity.DemoListDataModel previousItem,@FromEvent DemoListActivity.DemoListDataModel nextItem){
  return previousItem == nextItem;
}
