@Override @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class) public void create(SysUser user){
  sysUserMapper.insert(user);
}
