/** 
 * Called during DataDiffSection's diffing to determine if two objects contain the same data. This is used to detect of contents of an item have changed. See  {@link androidx.recyclerview.widget.DiffUtil.Callback#areContentsTheSame(int,int)} for more info.
 * @return true if the two objects contain the same data.
 */
@OnEvent(OnCheckIsSameContentEvent.class) static boolean isSameContent(ComponentContext c,@FromEvent DemoListActivity.DemoListDataModel previousItem,@FromEvent DemoListActivity.DemoListDataModel nextItem){
  return previousItem == null ? nextItem == null : previousItem.name == null ? nextItem.name == null : nextItem.name.equals(previousItem.name);
}
