@Override public void bindData(final @Nullable Object data) throws Exception {
  final RewardsItem rewardsItem=requireNonNull((RewardsItem)data);
  final String title=this.ksString.format("rewards_info_item_quantity_title",rewardsItem.quantity(),"quantity",ObjectUtils.toString(rewardsItem.quantity()),"title",rewardsItem.item().name());
  this.titleTextView.setText(title);
}
