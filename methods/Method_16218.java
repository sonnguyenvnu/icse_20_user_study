@Override public int updateByPk(String s,RoleEntity entity){
  entity.setStatus(null);
  return super.updateByPk(s,entity);
}
