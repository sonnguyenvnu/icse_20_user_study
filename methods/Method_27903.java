@OnClick(R.id.filter_sheet_search_btn) public void startSearch(){
  if (listener != null) {
    Intent intent=SearchUserActivity.Companion.getIntent(getContext(),listener.getLogin(),"");
    startActivity(intent);
  }
  dismiss();
}
