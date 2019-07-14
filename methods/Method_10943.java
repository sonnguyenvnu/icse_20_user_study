/** 
 * ?????listView??????????????
 * @param listView
 */
public static void fixListViewHeight(ListView listView){
  ListAdapter listAdapter=listView.getAdapter();
  int totalHeight=0;
  if (listAdapter == null) {
    return;
  }
  for (int index=0, len=listAdapter.getCount(); index < len; index++) {
    View listViewItem=listAdapter.getView(index,null,listView);
    listViewItem.measure(0,0);
    totalHeight+=listViewItem.getMeasuredHeight();
  }
  ViewGroup.LayoutParams params=listView.getLayoutParams();
  params.height=totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
  listView.setLayoutParams(params);
}
