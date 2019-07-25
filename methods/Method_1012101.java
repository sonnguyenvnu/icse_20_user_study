private void init(){
  for (  SModule module : Sequence.fromIterable(myRepo.getModules())) {
    if (module instanceof Language) {
      myListener.startListening((Language)module);
    }
  }
}
