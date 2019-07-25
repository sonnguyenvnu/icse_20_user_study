public static BusinessDetailFragment create(Business business){
  BusinessDetailFragment fragment=new BusinessDetailFragment();
  if (business != null) {
    Bundle bundle=new Bundle();
    bundle.putSerializable(Display.PARAM_OBJ,business);
    fragment.setArguments(bundle);
  }
  return fragment;
}
