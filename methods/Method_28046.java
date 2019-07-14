@Override public void onShowDetails(@NonNull Release item){
  if (!InputHelper.isEmpty(item.getBody())) {
    MessageDialogView.newInstance(!InputHelper.isEmpty(item.getName()) ? item.getName() : item.getTagName(),item.getBody(),true,false).show(getChildFragmentManager(),MessageDialogView.TAG);
  }
 else {
    showErrorMessage(getString(R.string.no_body));
  }
}
