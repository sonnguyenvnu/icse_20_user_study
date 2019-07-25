void compact(){
  for (int i=0; i < catchLabels.size(); i++) {
    while (catchLabels.get(i).isEmpty()) {
      catchLabels.set(i,catchLabels.get(i).primarySuccessor);
    }
  }
  while (primarySuccessor != null && primarySuccessor.isEmpty()) {
    primarySuccessor=primarySuccessor.primarySuccessor;
  }
  while (alternateSuccessor != null && alternateSuccessor.isEmpty()) {
    alternateSuccessor=alternateSuccessor.primarySuccessor;
  }
}
