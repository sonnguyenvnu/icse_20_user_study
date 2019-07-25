public MakeActionParameters models(Iterable<SModel> models){
  myModels=(models != null ? ListSequence.fromListWithValues(new ArrayList<SModel>(),models) : null);
  return this;
}
