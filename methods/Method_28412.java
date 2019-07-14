@Override public void onNotifyAdapter(@Nullable SearchHistory query){
  if (query == null)   getAdapter().notifyDataSetChanged();
 else   getAdapter().add(query);
}
