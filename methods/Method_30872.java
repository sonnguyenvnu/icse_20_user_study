private void saveViewState(int position,@NonNull View view){
  SparseArray<Parcelable> viewState=new SparseArray<>();
  view.saveHierarchyState(viewState);
  mViewStates.put(position,viewState);
}
