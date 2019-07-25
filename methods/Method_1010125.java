@Override public void load(Memento memento){
  String value=memento.get("planModel");
  myPlanModel=value == null ? null : PersistenceFacade.getInstance().createModelReference(value);
}
