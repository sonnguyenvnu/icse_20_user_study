private void showDeleteBottomSheet(){
  MediaUtils.deleteMedia(getContext(),adapter.getSelected(),getChildFragmentManager(),new ProgressBottomSheet.Listener<Media>(){
    @Override public void onCompleted(){
      adapter.invalidateSelectedCount();
    }
    @Override public void onProgress(    Media item){
      adapter.removeSelectedMedia(item);
    }
  }
);
}
