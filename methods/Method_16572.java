@Override @Cacheable(key="'ids:'+(#id==null?0:#id.hashCode())") public List<DistrictEntity> selectByPk(List<String> id){
  return super.selectByPk(id);
}
