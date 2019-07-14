@Override public void scrutinize(ItemUpdate update){
  super.scrutinize(update);
  for (  MonolingualTextValue label : update.getLabels()) {
    scrutinize(label);
  }
  for (  MonolingualTextValue alias : update.getAliases()) {
    scrutinize(alias);
  }
  for (  MonolingualTextValue description : update.getDescriptions()) {
    scrutinize(description);
  }
}
