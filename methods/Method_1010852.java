private void upgrade(DefaultSModelDescriptor model){
  model.deleteModelImport(model.getReference());
  model.load();
  model.setPersistenceVersion(LAST_VERSION);
  model.setChanged(true);
  model.save();
}
