/** 
 * Get the current List - any diffing to present this list has already been computed and dispatched via the ListUpdateCallback. <p> If a <code>null</code> List, or no List has been submitted, an empty list will be returned. <p> The returned list may not be mutated - mutations to content must be done through {@link #submitList(List)}.
 * @return current List.
 */
@AnyThread @NonNull public List<? extends EpoxyModel<?>> getCurrentList(){
  return readOnlyList;
}
