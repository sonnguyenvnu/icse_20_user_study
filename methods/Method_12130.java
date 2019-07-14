public static Userinfo empty(Long id){
  Userinfo userinfo=new Userinfo();
  userinfo.setId(id);
  userinfo.setBitState(BitStatesUtils.OP_BASIC_INFO);
  return userinfo;
}
