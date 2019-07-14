public RpAccount getByUserNo(Map<String,Object> map){
  return this.getSessionTemplate().selectOne(getStatement("getByUserNo"),map);
}
