@Override public void onToggle(long position,boolean isCollapsed){
  CommitFileChanges model=adapter.getItem((int)position);
  if (model == null)   return;
  if (model.getCommitFileModel().getPatch() == null) {
    if ("renamed".equalsIgnoreCase(model.getCommitFileModel().getStatus())) {
      SchemeParser.launchUri(getContext(),model.getCommitFileModel().getBlobUrl());
      return;
    }
    ActivityHelper.startCustomTab(getActivity(),adapter.getItem((int)position).getCommitFileModel().getBlobUrl());
  }
  toggleMap.put(position,isCollapsed);
}
