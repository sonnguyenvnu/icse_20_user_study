/** 
 * ???????View??
 * @param views
 */
public void setViews(final List<View> views){
  if (views == null || views.size() == 0) {
    return;
  }
  removeAllViews();
  for (int i=0; i < views.size(); i++) {
    final int position=i;
    views.get(i).setOnClickListener(new OnClickListener(){
      @Override public void onClick(      View v){
        if (onItemClickListener != null) {
          onItemClickListener.onItemClick(position,views.get(position));
        }
      }
    }
);
    addView(views.get(i));
  }
  startFlipping();
}
