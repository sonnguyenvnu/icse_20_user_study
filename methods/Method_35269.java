@Override protected void onViewBound(@NonNull View view){
  super.onViewBound(view);
  tvTitle.setText(title);
  imgDot.getDrawable().setColorFilter(ContextCompat.getColor(getActivity(),dotColor),Mode.SRC_ATOP);
  ViewCompat.setTransitionName(tvTitle,getResources().getString(R.string.transition_tag_title_indexed,fromPosition));
  ViewCompat.setTransitionName(imgDot,getResources().getString(R.string.transition_tag_dot_indexed,fromPosition));
  recyclerView.setHasFixedSize(true);
  recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),2));
  recyclerView.setAdapter(new CityGridAdapter(LayoutInflater.from(view.getContext()),CITY_MODELS));
}
