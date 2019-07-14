@Override public boolean checkUsername(String name,int userType){
  return this.loginInfoMapper.getCountByNickname(name,userType) <= 0;
}
