protected List<Predicate> filter(QJpaAlert alert){
  List<Predicate> preds=new ArrayList<>();
  if (getStates().size() > 0) {
    preds.add(alert.state.in(getStates()));
  }
  if (getLevels().size() > 0) {
    preds.add(alert.level.in(getLevels()));
  }
  if (getAfterTime() != null) {
    preds.add(alert.createdTime.gt(getAfterTime()));
  }
  if (getBeforeTime() != null) {
    preds.add(alert.createdTime.lt(getBeforeTime()));
  }
  if (getAfterTime() != null) {
    preds.add(alert.createdTime.gt(getAfterTime()));
  }
  if (getBeforeTime() != null) {
    preds.add(alert.createdTime.lt(getBeforeTime()));
  }
  if (!isIncludeCleared()) {
    preds.add(alert.cleared.isFalse());
  }
  if (getTypes().size() > 0) {
    BooleanBuilder likes=new BooleanBuilder();
    getTypes().stream().map(uri -> alert.typeString.like(uri.toASCIIString().concat("%"))).forEach(pred -> likes.or(pred));
    preds.add(likes);
  }
  if (getSubtypes().size() > 0) {
    preds.add(alert.subtype.in(getSubtypes()));
  }
  return preds;
}
