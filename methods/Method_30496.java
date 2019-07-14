public ApiRequest<DiaryList> getDiaryList(String userIdOrUid,Integer start,Integer count){
  return mFrodoService.getDiaryList(userIdOrUid,start,count);
}
