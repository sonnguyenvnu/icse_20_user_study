/** 
 * Take current params from activity and pass to the appropriate fragment.
 */
public void takeParams(final @NonNull DiscoveryParams params){
  Observable.from(this.fragments).filter(DiscoveryFragment::isInstantiated).filter(DiscoveryFragment::isAttached).filter(frag -> {
    final int fragmentPosition=frag.getArguments().getInt(ArgumentsKey.DISCOVERY_SORT_POSITION);
    return DiscoveryUtils.positionFromSort(params.sort()) == fragmentPosition;
  }
).subscribe(frag -> frag.updateParams(params));
}
