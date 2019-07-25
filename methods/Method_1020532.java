public static ProductFragment create(Business business){
  ProductFragment fragment=new ProductFragment();
  if (business != null) {
    Bundle bundle=new Bundle();
    bundle.putSerializable(Display.PARAM_OBJ,business);
    fragment.setArguments(bundle);
  }
  return fragment;
}
