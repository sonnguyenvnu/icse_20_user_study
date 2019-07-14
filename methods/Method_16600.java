@Override @Cacheable(key="'auth:persion-id'+#personId") public PersonnelAuthentication getPersonnelAuthorizationByPersonId(String personId){
  PersonEntity entity=selectByPk(personId);
  if (null == entity) {
    return null;
  }
  SimplePersonnelAuthentication authorization=new SimplePersonnelAuthentication();
  Personnel personnel=entityFactory.newInstance(Personnel.class,SimplePersonnel.class,entity);
  authorization.setPersonnel(personnel);
  Set<String> positionIds=DefaultDSLQueryService.createQuery(personPositionDao).where(PersonPositionEntity.personId,personId).listNoPaging().stream().map(PersonPositionEntity::getPositionId).collect(Collectors.toSet());
  Map<String,DepartmentEntity> departmentCache=new HashMap<>();
  Map<String,PositionEntity> positionCache=new HashMap<>();
  Map<String,OrganizationalEntity> orgCache=new HashMap<>();
  Map<String,DistrictEntity> districtCache=new HashMap<>();
  List<PositionEntity> positionEntities=getAllChildrenAndReturnRootNode(positionDao,positionIds,PositionEntity::setChildren,rootPosList -> {
    Set<String> departmentIds=rootPosList.stream().peek(positionEntity -> positionCache.put(positionEntity.getId(),positionEntity)).map(PositionEntity::getDepartmentId).collect(Collectors.toSet());
    if (!CollectionUtils.isEmpty(departmentIds)) {
      List<DepartmentEntity> departmentEntities=getAllChildrenAndReturnRootNode(departmentDao,departmentIds,DepartmentEntity::setChildren,rootDepList -> {
        Set<String> orgIds=rootDepList.stream().peek(departmentEntity -> departmentCache.put(departmentEntity.getId(),departmentEntity)).map(DepartmentEntity::getOrgId).collect(Collectors.toSet());
        if (!CollectionUtils.isEmpty(orgIds)) {
          List<OrganizationalEntity> orgEntities=getAllChildrenAndReturnRootNode(organizationalDao,orgIds,OrganizationalEntity::setChildren,rootOrgList -> {
            Set<String> districtIds=rootOrgList.stream().peek(org -> orgCache.put(org.getId(),org)).map(OrganizationalEntity::getDistrictId).filter(Objects::nonNull).collect(Collectors.toSet());
            if (!CollectionUtils.isEmpty(districtIds)) {
              List<DistrictEntity> districtEntities=getAllChildrenAndReturnRootNode(districtDao,districtIds,DistrictEntity::setChildren,rootDistrictList -> rootDistrictList.forEach(dist -> districtCache.put(dist.getId(),dist)));
              authorization.setDistrictIds(transformationTreeNode(null,districtEntities));
            }
          }
);
          authorization.setOrgIds(transformationTreeNode(null,orgEntities));
        }
      }
);
      authorization.setDepartmentIds(transformationTreeNode(null,departmentEntities));
    }
  }
);
  authorization.setPositionIds(transformationTreeNode(null,positionEntities));
  Set<Position> positions=positionEntities.stream().map(positionEntity -> {
    DepartmentEntity departmentEntity=departmentCache.get(positionEntity.getDepartmentId());
    if (departmentEntity == null) {
      return null;
    }
    OrganizationalEntity organizationalEntity=orgCache.get(departmentEntity.getOrgId());
    if (organizationalEntity == null) {
      return null;
    }
    DistrictEntity districtEntity=districtCache.get(organizationalEntity.getDistrictId());
    District district=districtEntity == null ? null : SimpleDistrict.builder().code(districtEntity.getCode()).id(districtEntity.getId()).name(districtEntity.getName()).fullName(districtEntity.getFullName()).build();
    Organization organization=SimpleOrganization.builder().id(organizationalEntity.getId()).name(organizationalEntity.getName()).fullName(organizationalEntity.getFullName()).code(organizationalEntity.getCode()).district(district).build();
    Department department=SimpleDepartment.builder().id(departmentEntity.getId()).name(departmentEntity.getName()).code(departmentEntity.getCode()).org(organization).build();
    return SimplePosition.builder().id(positionEntity.getId()).name(positionEntity.getName()).department(department).code("").build();
  }
).filter(Objects::nonNull).collect(Collectors.toSet());
  authorization.setPositions(positions);
  List<RelationInfoEntity> relationInfoList=DefaultDSLQueryService.createQuery(relationInfoDao).where(RelationInfoEntity.relationFrom,personId).or(RelationInfoEntity.relationTo,personId).listNoPaging();
  List<Relation> relations=relationInfoList.stream().map(info -> {
    SimpleRelation relation=new SimpleRelation();
    relation.setDimension(info.getRelationTypeFrom());
    relation.setTarget(info.getRelationTo());
    relation.setRelation(info.getRelationId());
    if (personId.equals(info.getRelationFrom())) {
      relation.setDirection(Relation.Direction.POSITIVE);
    }
 else {
      relation.setDirection(Relation.Direction.REVERSE);
    }
    return relation;
  }
).collect(Collectors.toList());
  authorization.setRelations(new SimpleRelations(relations));
  return authorization;
}
