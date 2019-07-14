@SuppressWarnings("unchecked") public <V extends View>V findViewById(int viewId){
  View view=mViews.get(viewId);
  if (view == null) {
    view=itemView.findViewById(viewId);
    mViews.put(viewId,view);
  }
  return (V)view;
}
