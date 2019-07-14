@Override @Cacheable(key="'auth-bind:'+#id") public PersonAuthBindEntity selectAuthBindByPk(String id){
  PersonEntity personEntity=this.selectByPk(id);
  if (personEntity == null) {
    return null;
  }
  if (personEntity instanceof PersonAuthBindEntity) {
    return ((PersonAuthBindEntity)personEntity);
  }
  PersonAuthBindEntity bindEntity=entityFactory.newInstance(PersonAuthBindEntity.class,personEntity);
  Set<String> positionIds=DefaultDSLQueryService.createQuery(personPositionDao).where(PersonPositionEntity.personId,id).listNoPaging().stream().map(PersonPositionEntity::getPositionId).collect(Collectors.toSet());
  bindEntity.setPositionIds(positionIds);
  if (null != userService && null != personEntity.getUserId()) {
    UserEntity userEntity=userService.selectByPk(personEntity.getUserId());
    if (null != userEntity) {
      PersonUserEntity entity=entityFactory.newInstance(PersonUserEntity.class);
      entity.setUsername(userEntity.getUsername());
      bindEntity.setPersonUser(entity);
    }
  }
  return bindEntity;
}
