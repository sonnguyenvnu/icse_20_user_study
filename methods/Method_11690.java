@Override public MultiPageModel combine(MultiPageModel multiPageModel){
  News163 news163=new News163();
  news163.title=this.title;
  News163 pagedModel1=(News163)multiPageModel;
  news163.content=this.content + pagedModel1.content;
  return news163;
}
