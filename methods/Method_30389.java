private void init(){
  setDividerDrawable(AppCompatResources.getDrawable(getContext(),R.drawable.transparent_divider_horizontal_2dp));
  setShowDividers(SHOW_DIVIDER_MIDDLE);
  setColumnShrinkable(1,true);
  setColumnStretchable(1,true);
  for (int i=0; i < mRowHolders.length; ++i) {
    View rowView=ViewUtils.inflate(R.layout.item_rating_distribution_layout_row,this);
    RowHolder rowHolder=new RowHolder(rowView);
    rowHolder.mStarsText.setText(makeStars(mRowHolders.length - i));
    rowHolder.mBarView.setBackgroundColor(COLORS[i]);
    mRowHolders[i]=rowHolder;
    addView(rowView);
  }
}
