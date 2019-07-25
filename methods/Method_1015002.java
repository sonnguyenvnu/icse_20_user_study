public void search(String keyword){
  if (TextUtils.isEmpty(keyword)) {
    return;
  }
  List<UIUserInfo> result=pickContactViewModel.searchContact(keyword);
  if (result == null || result.isEmpty()) {
    contactRecyclerView.setVisibility(View.GONE);
    tipTextView.setVisibility(View.VISIBLE);
  }
 else {
    contactRecyclerView.setVisibility(View.VISIBLE);
    tipTextView.setVisibility(View.GONE);
  }
  contactAdapter.setContacts(result);
  contactAdapter.notifyDataSetChanged();
}
