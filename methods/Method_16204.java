@Override public int deleteByGroupId(String groupId){
  tryValidateProperty(groupId != null,MenuGroupBindEntity.groupId,"groups id can not be null");
  return createDelete().where(MenuGroupBindEntity.groupId,groupId).exec();
}
