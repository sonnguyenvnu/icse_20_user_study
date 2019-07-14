@Override protected void onViewBound(@NonNull View view){
  super.onViewBound(view);
  recyclerView.setHasFixedSize(true);
  recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
  recyclerView.setAdapter(new CityDetailAdapter(LayoutInflater.from(view.getContext()),title,imageDrawableRes,LIST_ROWS,title));
}
