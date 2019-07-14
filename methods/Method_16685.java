public static GroupEntity toActivitiGroup(RoleEntity sysRole){
  GroupEntity groupEntity=new GroupEntity();
  groupEntity.setId(sysRole.getId());
  groupEntity.setRevision(1);
  groupEntity.setType("assignment");
  groupEntity.setName(sysRole.getName());
  return groupEntity;
}
