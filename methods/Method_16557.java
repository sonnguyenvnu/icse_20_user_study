public Stream<Relation> relationStream(Supplier<List<String>> supplier){
  List<String> personIdList=supplier.get();
  QueryParamEntity queryParamEntity=query.end().and().nest().in("relationFrom",personIdList).or().in("relationTo",personIdList).end().getParam();
  return serviceContext.getRelationInfoService().select(queryParamEntity).stream().map(info -> {
    SimpleRelation relation=new SimpleRelation();
    relation.setTarget(info.getRelationTo());
    relation.setTargetObject(RelationTargetHolder.get(info.getRelationTypeTo(),info.getRelationTo()).orElse(null));
    relation.setRelation(info.getRelationId());
    if (personIdList.contains(info.getRelationFrom())) {
      relation.setDimension(info.getRelationTypeFrom());
      relation.setDirection(Relation.Direction.POSITIVE);
    }
 else {
      relation.setDimension(info.getRelationTypeTo());
      relation.setDirection(Relation.Direction.REVERSE);
    }
    return relation;
  }
);
}
