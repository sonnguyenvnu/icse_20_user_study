@Override public void onNotifyAdapter(@Nullable List<GroupedNotificationModel> items){
  hideProgress();
  if (items == null || items.isEmpty()) {
    adapter.clear();
    return;
  }
  adapter.insertItems(items);
  if (isSafe())   getActivity().invalidateOptionsMenu();
}
