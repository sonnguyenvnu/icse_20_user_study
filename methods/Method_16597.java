protected void syncPositionInfo(String personId,Set<String> positionIds){
  for (  String positionId : positionIds) {
    PersonPositionEntity positionEntity=entityFactory.newInstance(PersonPositionEntity.class);
    positionEntity.setPersonId(personId);
    positionEntity.setPositionId(positionId);
    this.personPositionDao.insert(positionEntity);
  }
}
