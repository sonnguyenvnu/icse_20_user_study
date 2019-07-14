protected void bindItemCollectionHolder(RecyclerView.ViewHolder holder,T item){
  ItemCollectionHolder itemCollectionHolder=(ItemCollectionHolder)holder;
  CollectableItem.Type type=item.getType();
  itemCollectionHolder.todoButton.setText(ItemCollectionState.TODO.getString(type,itemCollectionHolder.todoButton.getContext()));
  itemCollectionHolder.doingButton.setText(ItemCollectionState.DOING.getString(type,itemCollectionHolder.doingButton.getContext()));
  itemCollectionHolder.doneButton.setText(ItemCollectionState.DONE.getString(type,itemCollectionHolder.doneButton.getContext()));
  SimpleItemCollection itemCollection=item.collection;
  ItemCollectionState state=itemCollection != null ? itemCollection.getState() : null;
  boolean todoVisible=itemCollection == null;
  ViewUtils.setVisibleOrGone(itemCollectionHolder.todoButton,todoVisible);
  itemCollectionHolder.todoButton.setOnClickListener(view -> {
    Context context=view.getContext();
    context.startActivity(ItemCollectionActivity.makeIntent(item,ItemCollectionState.TODO,context));
  }
);
  boolean doingVisible=item.getType().hasDoingState() && (itemCollection == null || state == ItemCollectionState.TODO);
  ViewUtils.setVisibleOrGone(itemCollectionHolder.doingButton,doingVisible);
  itemCollectionHolder.doingButton.setOnClickListener(view -> {
    Context context=view.getContext();
    context.startActivity(ItemCollectionActivity.makeIntent(item,ItemCollectionState.DOING,context));
  }
);
  boolean doneVisible=itemCollection == null || state == ItemCollectionState.TODO || state == ItemCollectionState.DOING;
  ViewUtils.setVisibleOrGone(itemCollectionHolder.doneButton,doneVisible);
  itemCollectionHolder.doneButton.setOnClickListener(view -> {
    Context context=view.getContext();
    context.startActivity(ItemCollectionActivity.makeIntent(item,ItemCollectionState.DONE,context));
  }
);
  boolean buttonBarVisible=todoVisible || doingVisible || doneVisible;
  ViewUtils.setVisibleOrGone(itemCollectionHolder.buttonBar,buttonBarVisible);
  ViewUtils.setVisibleOrGone(itemCollectionHolder.buttonBarSpace,!buttonBarVisible);
  boolean hasItemCollection=itemCollection != null;
  ViewUtils.setVisibleOrGone(itemCollectionHolder.itemCollectionLayout,hasItemCollection);
  if (hasItemCollection) {
    itemCollectionHolder.stateText.setText(state.getString(item.getType(),itemCollectionHolder.stateText.getContext()));
    boolean hasRating=itemCollection.rating != null;
    ViewUtils.setVisibleOrGone(itemCollectionHolder.ratingBar,hasRating);
    if (hasRating) {
      itemCollectionHolder.ratingBar.setRating(itemCollection.rating.getRatingBarRating());
    }
    itemCollectionHolder.dateText.setText(TimeUtils.formatDate(TimeUtils.parseDoubanDateTime(itemCollection.createTime),itemCollectionHolder.dateText.getContext()));
    itemCollectionHolder.commentText.setText(itemCollection.comment);
    itemCollectionHolder.itemCollectionLayout.setOnClickListener(view -> {
      Context context=view.getContext();
      context.startActivity(ItemCollectionActivity.makeIntent(item,context));
    }
);
    itemCollectionHolder.menu.setOnMenuItemClickListener(menuItem -> {
switch (menuItem.getItemId()) {
case R.id.action_edit:
{
          Context context=RecyclerViewUtils.getContext(holder);
          context.startActivity(ItemCollectionActivity.makeIntent(item,context));
          return true;
        }
case R.id.action_uncollect:
      mListener.onUncollectItem(item);
    return true;
default :
  return false;
}
}
);
}
ViewUtils.setVisibleOrGone(itemCollectionHolder.dividerView,!hasItemCollection);
}
