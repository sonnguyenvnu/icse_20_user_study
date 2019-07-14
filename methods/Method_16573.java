@Override @Cacheable(key="'id:'+#id") public DistrictEntity selectByPk(String id){
  return super.selectByPk(id);
}
