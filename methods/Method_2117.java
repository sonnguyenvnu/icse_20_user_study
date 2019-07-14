@Override public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  final ComponentContext componentContext=new ComponentContext(getContext());
  FrameLayout container=view.findViewById(R.id.container);
  container.addView(LithoView.create(componentContext,createComponent(componentContext)));
}
