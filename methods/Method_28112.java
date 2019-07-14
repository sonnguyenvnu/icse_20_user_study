@OnClick(R.id.submit) void onSubmit(){
  boolean isEmpty=InputHelper.isEmpty(comment);
  if (!isEmpty) {
    getPresenter().onSubmit(getArguments().getString(BundleConstant.EXTRA_SEVEN),getArguments().getString(BundleConstant.EXTRA_EIGHT),getArguments().getInt(BundleConstant.ID),InputHelper.toString(comment));
  }
 else {
    showMessage(R.string.error,R.string.required_field);
  }
}
