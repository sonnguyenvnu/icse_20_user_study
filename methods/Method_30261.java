protected void addTextToData(int titleRes,String text,List<Pair<String,String>> data){
  if (!TextUtils.isEmpty(text)) {
    String title=getString(titleRes);
    data.add(new Pair<>(title,text));
  }
}
