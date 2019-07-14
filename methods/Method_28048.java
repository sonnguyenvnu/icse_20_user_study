@Override public void onItemSelected(SimpleUrlsModel item){
  if (ActivityHelper.checkAndRequestReadWritePermission(getActivity())) {
    RestProvider.downloadFile(getContext(),item.getUrl());
  }
}
