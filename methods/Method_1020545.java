public void refresh(){
  int childCount=getChildCount();
  if (childCount > 0) {
    int visibleChildCount=0;
    for (int i=0; i < childCount; i++) {
      RelativeLayout itemView=(RelativeLayout)getChildAt(i);
      View divider=itemView.findViewById(R.id.divider);
      if (divider != null) {
        divider.setVisibility(VISIBLE);
      }
      if (itemView.getVisibility() != GONE) {
        visibleChildCount++;
      }
    }
    hideLastItemLine(visibleChildCount);
  }
}
