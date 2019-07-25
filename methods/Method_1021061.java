@Override public String dump(){
  String rankingContent=getRankingDetail(20);
  String summary="Total count of found block method is " + methodBlockDetails.size() + " (Over " + threshold() + "ms)";
  Log.i(TAG,summary);
  Log.i(TAG,rankingContent);
  return summary + rankingContent;
}
