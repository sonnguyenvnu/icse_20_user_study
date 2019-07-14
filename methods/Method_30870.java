@NonNull @Override public final View instantiateItem(@NonNull ViewGroup container,int position){
  View view=onCreateView(container,position);
  restoreViewState(position,view);
  mViews.put(position,view);
  return view;
}
