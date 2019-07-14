@Override protected void onProgressUpdate(Pair<Integer,Long>[] dataArr){
  Pair<Integer,Long> data=dataArr[0];
  itemsText.setText(getText(data.first,data.second,true));
}
