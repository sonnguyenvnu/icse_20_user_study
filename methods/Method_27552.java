@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  select.setVisibility(isLink() ? View.GONE : View.VISIBLE);
  if (savedInstanceState == null) {
    title.getEditText().setText(getArguments().getString(BundleConstant.ITEM));
  }
}
