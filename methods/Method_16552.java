public List<PersonEntity> getAllPerson(){
  List<PositionEntity> positionEntities=positionSupplier.get();
  if (CollectionUtils.isEmpty(positionEntities)) {
    return new java.util.ArrayList<>();
  }
  return serviceContext.getPersonService().selectByPositionIds(positionEntities.stream().map(PositionEntity::getId).collect(Collectors.toList()));
}
