@Override public void onItemClick(int position,View v,CommitFileChanges model){
  if (v.getId() == R.id.patchList) {
    sendToView(view -> view.onOpenForResult(position,model));
  }
 else   if (v.getId() == R.id.open) {
    CommitFileModel item=model.getCommitFileModel();
    PopupMenu popup=new PopupMenu(v.getContext(),v);
    MenuInflater inflater=popup.getMenuInflater();
    inflater.inflate(R.menu.commit_row_menu,popup.getMenu());
    popup.setOnMenuItemClickListener(item1 -> {
switch (item1.getItemId()) {
case R.id.open:
        v.getContext().startActivity(CodeViewerActivity.createIntent(v.getContext(),item.getContentsUrl(),item.getBlobUrl()));
      break;
case R.id.share:
    ActivityHelper.shareUrl(v.getContext(),item.getBlobUrl());
  break;
case R.id.download:
Activity activity=ActivityHelper.getActivity(v.getContext());
if (activity == null) break;
if (ActivityHelper.checkAndRequestReadWritePermission(activity)) {
RestProvider.downloadFile(v.getContext(),item.getRawUrl());
}
break;
case R.id.copy:
AppHelper.copyToClipboard(v.getContext(),item.getBlobUrl());
break;
}
return true;
}
);
popup.show();
}
}
