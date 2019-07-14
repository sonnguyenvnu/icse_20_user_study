private void fetchContacts(){
  phoneBookContacts=new ArrayList<>(ContactsController.getInstance(currentAccount).phoneBookContacts);
  Collections.sort(phoneBookContacts,(o1,o2) -> {
    if (o1.imported > o2.imported) {
      return -1;
    }
 else     if (o1.imported < o2.imported) {
      return 1;
    }
    return 0;
  }
);
  if (emptyView != null) {
    emptyView.showTextView();
  }
  if (adapter != null) {
    adapter.notifyDataSetChanged();
  }
}
