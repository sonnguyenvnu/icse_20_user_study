@Override protected void onViewBound(@NonNull View view){
  super.onViewBound(view);
  recyclerView.setHasFixedSize(true);
  recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
  recyclerView.setAdapter(new DetailItemAdapter(LayoutInflater.from(view.getContext()),DetailItemModel.values()));
  twoPaneView=(detailContainer != null);
  if (twoPaneView) {
    onRowSelected(selectedIndex);
  }
}
