@Transactional(rollbackFor=Exception.class) @Override public Boolean update(SysUserVo sysUserVo){
  SysUser sysUser=new SysUser();
  BeanUtils.copyProperties(sysUserVo,sysUser);
  this.updateById(sysUser);
  deleteUserWithRole(sysUserVo.getUserId());
  bindUserWithRole(sysUserVo);
  return Boolean.TRUE;
}
