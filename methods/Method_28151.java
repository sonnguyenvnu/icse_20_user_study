@NonNull @Override public ArrayList<String> getNamesToTag(){
  IssueTimelineFragment fragment=getIssueTimelineFragment();
  if (fragment != null) {
    return fragment.getNamesToTag();
  }
  return new ArrayList<>();
}
