private void restoreViewState(int position,@NonNull View view){
  SparseArray<Parcelable> viewState=mViewStates.get(position);
  if (viewState != null) {
    view.restoreHierarchyState(viewState);
  }
}
