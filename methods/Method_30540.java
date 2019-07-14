public me.zhanghai.android.douya.network.api.info.frodo.TextEntity toFrodo(){
  me.zhanghai.android.douya.network.api.info.frodo.TextEntity entity=new me.zhanghai.android.douya.network.api.info.frodo.TextEntity();
  entity.end=end;
  entity.start=start;
  entity.title=title;
  entity.uri=href;
  return entity;
}
