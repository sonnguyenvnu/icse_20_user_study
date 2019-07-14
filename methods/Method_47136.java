public void computeScroll(){
  View vi=listView.getChildAt(0);
  int top=(vi == null) ? 0 : vi.getTop();
  int index;
  if (IS_LIST)   index=mLayoutManager.findFirstVisibleItemPosition();
 else   index=mLayoutManagerGrid.findFirstVisibleItemPosition();
  Bundle b=new Bundle();
  b.putInt("index",index);
  b.putInt("top",top);
  scrolls.put(CURRENT_PATH,b);
}
