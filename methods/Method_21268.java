public void loadParams(final @NonNull DiscoveryParams params){
  final DiscoveryActivity activity=(DiscoveryActivity)getContext();
  this.filterTextView.setText(params.filterString(activity,this.ksString,true,false));
}
