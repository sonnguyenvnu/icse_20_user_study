@Override protected void onLoadFinished(boolean more,int count,boolean successful,List<Diary> response,ApiError error){
  if (successful) {
    if (more) {
      append(response);
      getListener().onLoadDiaryListFinished(getRequestCode());
      getListener().onDiaryListAppended(getRequestCode(),Collections.unmodifiableList(response));
    }
 else {
      set(response);
      getListener().onLoadDiaryListFinished(getRequestCode());
      getListener().onDiaryListChanged(getRequestCode(),Collections.unmodifiableList(get()));
    }
    for (    Diary diary : response) {
      EventBusUtils.postAsync(new DiaryUpdatedEvent(diary,this));
    }
  }
 else {
    getListener().onLoadDiaryListFinished(getRequestCode());
    getListener().onLoadDiaryListError(getRequestCode(),error);
  }
}
