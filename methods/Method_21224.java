public void scrollToTop(final int position){
  Observable.from(this.fragments).filter(DiscoveryFragment::isInstantiated).filter(DiscoveryFragment::isAttached).filter(frag -> {
    final int fragmentPosition=frag.getArguments().getInt(ArgumentsKey.DISCOVERY_SORT_POSITION);
    return position == fragmentPosition;
  }
).subscribe(DiscoveryFragment::scrollToTop);
}
