private void onShowFilterMenu(@Nullable TabsCountStateModel model,TextView tv){
  if (model == null)   return;
  PopupMenu popup=new PopupMenu(getContext(),tv);
  MenuInflater inflater=popup.getMenuInflater();
  inflater.inflate(R.menu.filter_issue_state_menu,popup.getMenu());
  popup.setOnMenuItemClickListener(item -> {
    if (pager == null || pager.getAdapter() == null)     return false;
    MyPullRequestFragment myIssuesFragment=(MyPullRequestFragment)pager.getAdapter().instantiateItem(pager,model.getTabIndex());
    if (myIssuesFragment == null)     return false;
switch (item.getItemId()) {
case R.id.opened:
      counts.remove(model);
    model.setDrawableId(R.drawable.ic_issue_opened_small);
  counts.add(model);
updateDrawable(model,tv);
myIssuesFragment.onFilterIssue(IssueState.open);
return true;
case R.id.closed:
counts.remove(model);
model.setDrawableId(R.drawable.ic_issue_closed_small);
counts.add(model);
updateDrawable(model,tv);
myIssuesFragment.onFilterIssue(IssueState.closed);
return true;
}
return false;
}
);
popup.show();
}
