@Override public void onChildDetachedFromWindow(View child){
  Object initialWidth=child.getTag(R.id.epoxy_recycler_view_child_initial_size_id);
  if (initialWidth instanceof Integer) {
    ViewGroup.LayoutParams params=child.getLayoutParams();
    params.width=(int)initialWidth;
    child.setTag(R.id.epoxy_recycler_view_child_initial_size_id,null);
  }
}
