@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  if (savedInstanceState == null) {
    String titleMsg=getArguments().getString(BundleConstant.EXTRA);
    if (!InputHelper.isEmpty(titleMsg)) {
      if (title.getEditText() != null)       title.getEditText().setText(titleMsg);
    }
  }
}
