@Override @Cacheable(key="'all'") public List<DistrictEntity> select(){
  return createQuery().where().orderByAsc(DistrictEntity.sortIndex).listNoPaging();
}
