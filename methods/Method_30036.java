@Subscribe(threadMode=ThreadMode.POSTING) public void onDiaryDeleted(DiaryDeletedEvent event){
  if (event.isFromMyself(this) || isEmpty()) {
    return;
  }
  List<Diary> diaryList=get();
  for (int i=0, size=diaryList.size(); i < size; ) {
    Diary diary=diaryList.get(i);
    if (diary.id == event.diaryId) {
      diaryList.remove(i);
      getListener().onDiaryRemoved(getRequestCode(),i);
      --size;
    }
 else {
      ++i;
    }
  }
}
