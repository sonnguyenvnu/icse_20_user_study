@Override @SuppressWarnings("all") public ResponseMessage<UserEntity> getByPrimaryKey(@PathVariable String id){
  return QueryController.super.getByPrimaryKey(id).exclude(UserEntity.class,"password","salt");
}
