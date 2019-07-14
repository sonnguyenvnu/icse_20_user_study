@SuppressWarnings("deprecation") public me.zhanghai.android.douya.network.api.info.frodo.SimpleUser toFrodo(){
  me.zhanghai.android.douya.network.api.info.frodo.SimpleUser simpleUser=new me.zhanghai.android.douya.network.api.info.frodo.SimpleUser();
  simpleUser.avatar=avatar;
  simpleUser.id=id;
  simpleUser.type=type;
  simpleUser.name=name;
  simpleUser.uid=uid;
  simpleUser.uri=DoubanUtils.makeUserUri(id);
  simpleUser.url=alt;
  return simpleUser;
}
