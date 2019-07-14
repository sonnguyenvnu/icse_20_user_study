@Override @Cacheable(key="'code:'+#code") public DistrictEntity selectByCode(String code){
  return createQuery().where(DistrictEntity.code,code).single();
}
