@Override protected View buildView(@NonNull ViewGroup parent){
  LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
  ViewDataBinding binding=DataBindingUtil.inflate(layoutInflater,getViewType(),parent,false);
  View view=binding.getRoot();
  view.setTag(binding);
  return view;
}
