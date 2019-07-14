public void setRecyclerView(@NonNull RecyclerView recyclerView,int columns){
  this.recyclerView=recyclerView;
  this.columns=columns;
  bar.setVisibility(INVISIBLE);
  recyclerView.addOnScrollListener(this.scrollListener);
  invalidateVisibility();
  recyclerView.setOnHierarchyChangeListener(new OnHierarchyChangeListener(){
    @Override public void onChildViewAdded(    View parent,    View child){
      invalidateVisibility();
    }
    @Override public void onChildViewRemoved(    View parent,    View child){
      invalidateVisibility();
    }
  }
);
}
