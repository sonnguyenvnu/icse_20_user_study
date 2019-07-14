@Override public void onInitPinnedRepos(@NonNull List<GetPinnedReposQuery.Node> nodes){
  if (pinnedReposTextView == null)   return;
  if (!nodes.isEmpty()) {
    pinnedReposTextView.setVisibility(VISIBLE);
    pinnedReposCard.setVisibility(VISIBLE);
    ProfilePinnedReposAdapter adapter=new ProfilePinnedReposAdapter(nodes);
    adapter.setListener(new BaseViewHolder.OnItemClickListener<GetPinnedReposQuery.Node>(){
      @Override public void onItemClick(      int position,      View v,      GetPinnedReposQuery.Node item){
        SchemeParser.launchUri(getContext(),item.url().toString());
      }
      @Override public void onItemLongClick(      int position,      View v,      GetPinnedReposQuery.Node item){
      }
    }
);
    pinnedList.addDivider();
    pinnedList.setAdapter(adapter);
  }
 else {
    pinnedReposTextView.setVisibility(GONE);
    pinnedReposCard.setVisibility(GONE);
  }
}
