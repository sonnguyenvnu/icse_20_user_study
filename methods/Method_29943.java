protected BroadcastLikerListFragment setArguments(Broadcast broadcast){
  FragmentUtils.getArgumentsBuilder(this).putParcelable(EXTRA_BROADCAST,broadcast);
  return this;
}
