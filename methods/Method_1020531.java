public static CommentFragment create(Business business){
  CommentFragment fragment=new CommentFragment();
  if (business != null) {
    Bundle bundle=new Bundle();
    bundle.putSerializable(Display.PARAM_OBJ,business);
    fragment.setArguments(bundle);
  }
  return fragment;
}
