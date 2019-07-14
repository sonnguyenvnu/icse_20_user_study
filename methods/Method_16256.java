@Cacheable(key="'all-defaults'") public List<DashBoardConfigEntity> selectAllDefaults(){
  return createQuery().where("defaultConfig",true).or().isNull("defaultConfig").listNoPaging();
}
