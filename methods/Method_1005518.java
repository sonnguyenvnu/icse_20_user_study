public void search(String key,int page){
  this.key=key;
  this.page=page;
  getPresenter().getLiveListByKey(key,page);
}
