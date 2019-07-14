/** 
 * Call when the view model tells us to clear specific pages.
 */
public void clearPages(final @NonNull List<Integer> pages){
  Observable.from(this.fragments).filter(DiscoveryFragment::isInstantiated).filter(DiscoveryFragment::isAttached).filter(frag -> {
    final int fragmentPosition=frag.getArguments().getInt(ArgumentsKey.DISCOVERY_SORT_POSITION);
    return pages.contains(fragmentPosition);
  }
).subscribe(DiscoveryFragment::clearPage);
}
