protected void bind(UserItems primaryItems,UserItems secondaryItems,UserItems tertiaryItems){
  final Context context=getContext();
  ItemCollectionState state=primaryItems.getState();
  CollectableItem.Type type=primaryItems.getType();
  String stateString=state.getString(type,context);
  mTitleText.setText(stateString);
  OnClickListener viewMoreListener=new OnClickListener(){
    @Override public void onClick(    View view){
      onViewPrimaryItems();
    }
  }
;
  mTitleText.setOnClickListener(viewMoreListener);
  mViewMoreText.setOnClickListener(viewMoreListener);
  mItemAdapter.replace(primaryItems.items);
  ViewUtils.setVisibleOrGone(mItemList,!primaryItems.items.isEmpty());
  mEmptyView.setText(context.getString(R.string.profile_items_empty_format,stateString,type.getName(context)));
  ViewUtils.setVisibleOrGone(mEmptyView,primaryItems.items.isEmpty());
  if (primaryItems.total > primaryItems.items.size()) {
    mViewMoreText.setText(context.getString(R.string.view_more_with_count_format,primaryItems.total));
  }
 else {
    mViewMoreText.setVisibility(GONE);
  }
  if (secondaryItems != null && secondaryItems.total > 0) {
    mSecondaryText.setText(context.getString(R.string.profile_items_non_primary_format,secondaryItems.getState().getString(secondaryItems.getType(),context),secondaryItems.total));
    mSecondaryText.setOnClickListener(new OnClickListener(){
      @Override public void onClick(      View view){
        onViewSecondaryItems();
      }
    }
);
  }
 else {
    mSecondaryText.setVisibility(GONE);
  }
  if (tertiaryItems != null && tertiaryItems.total > 0) {
    mTertiaryText.setText(context.getString(R.string.profile_items_non_primary_format,tertiaryItems.getState().getString(tertiaryItems.getType(),context),tertiaryItems.total));
    mTertiaryText.setOnClickListener(new OnClickListener(){
      @Override public void onClick(      View view){
        onViewTertiaryItems();
      }
    }
);
  }
 else {
    mTertiaryText.setVisibility(GONE);
  }
}
