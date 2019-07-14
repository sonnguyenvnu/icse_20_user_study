public static List<Group> toActivitiGroups(List<RoleEntity> roleEntities){
  List<Group> groups=new ArrayList<Group>();
  for (  RoleEntity roleEntity : roleEntities) {
    GroupEntity groupEntity=toActivitiGroup(roleEntity);
    groups.add(groupEntity);
  }
  return groups;
}
