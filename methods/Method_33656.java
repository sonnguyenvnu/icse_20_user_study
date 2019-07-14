private void getIntentData(){
  cid=getIntent().getIntExtra("cid",0);
  String chapterName=getIntent().getStringExtra("chapterName");
  setTitle(chapterName);
  mAdapter.setNoShowChapterName();
}
