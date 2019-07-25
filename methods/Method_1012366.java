@Override protected void convert(K holder,T data){
  if (holder.itemView instanceof SwipeMenuLayout) {
    SwipeMenuLayout swipeMenuLayout=(SwipeMenuLayout)holder.itemView;
    int childCount=swipeMenuLayout.getChildCount();
    for (int i=0; i < childCount; i++) {
      View childView=swipeMenuLayout.getChildAt(i);
      if (childView instanceof SwipeMenuView) {
        ((SwipeMenuView)childView).bindAdapterViewHolder(holder);
      }
    }
    convert(holder,data,true);
  }
 else {
    convert(holder,data,false);
  }
}
