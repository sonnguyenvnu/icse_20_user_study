/** 
 * Passes along root categories to its fragment position to help fetch appropriate projects.
 */
public void takeCategoriesForPosition(final @NonNull List<Category> categories,final int position){
  Observable.from(this.fragments).filter(DiscoveryFragment::isInstantiated).filter(DiscoveryFragment::isAttached).filter(frag -> {
    final int fragmentPosition=frag.getArguments().getInt(ArgumentsKey.DISCOVERY_SORT_POSITION);
    return fragmentPosition == position;
  }
).subscribe(frag -> frag.takeCategories(categories));
}
