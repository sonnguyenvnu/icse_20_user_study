public void rename(final Spec aNewSpec){
  final Collection<Model> models=getModels().values();
  for (  Model model : models) {
    model.specRename(aNewSpec);
  }
}
