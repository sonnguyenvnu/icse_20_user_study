@Override protected void onViewBound(@NonNull View view){
  super.onViewBound(view);
  recyclerView.setHasFixedSize(true);
  recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
  recyclerView.setAdapter(new HomeAdapter(LayoutInflater.from(view.getContext()),DemoModel.values()));
}
