@OnClick(R.id.filter_text_view) protected void textViewClick(){
  Timber.d("DiscoveryDrawerChildParamsViewHolder topFilterViewHolderRowClick");
  this.delegate.childFilterViewHolderRowClick(this,this.item);
}
