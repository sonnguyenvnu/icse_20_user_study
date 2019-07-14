@Subscribe(threadMode=ThreadMode.POSTING) public void onDiaryUpdated(DiaryUpdatedEvent event){
  if (event.isFromMyself(this) || isEmpty()) {
    return;
  }
  List<Diary> diaryList=get();
  for (int i=0, size=diaryList.size(); i < size; ++i) {
    Diary diary=diaryList.get(i);
    if (diary.id == event.diary.id) {
      diaryList.set(i,event.diary);
      getListener().onDiaryChanged(getRequestCode(),i,diaryList.get(i));
    }
  }
}
