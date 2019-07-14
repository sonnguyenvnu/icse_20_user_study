@Override public Relations getRelations(String relationTypeFrom,String target){
  Objects.requireNonNull(relationTypeFrom);
  Objects.requireNonNull(target);
  List<RelationInfoEntity> relationInfoList=DefaultDSLQueryService.createQuery(relationInfoDao).where(RelationInfoEntity.relationTypeFrom,relationTypeFrom).nest().is(RelationInfoEntity.relationFrom,target).or(RelationInfoEntity.relationTo,target).end().listNoPaging();
  List<Relation> relations=relationInfoList.stream().map(info -> {
    SimpleRelation relation=new SimpleRelation();
    relation.setDimension(info.getRelationTypeFrom());
    relation.setTarget(info.getRelationTo());
    relation.setTargetObject(RelationTargetHolder.get(info.getRelationTypeTo(),info.getRelationTo()).orElse(null));
    relation.setRelation(info.getRelationId());
    if (target.equals(info.getRelationFrom())) {
      relation.setDirection(Relation.Direction.POSITIVE);
    }
 else {
      relation.setDirection(Relation.Direction.REVERSE);
    }
    return relation;
  }
).collect(Collectors.toList());
  return new SimpleRelations(relations);
}
