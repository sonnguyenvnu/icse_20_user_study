public void dispose(){
  for (  SModule module : Sequence.fromIterable(myRepo.getModules())) {
    if (module instanceof Language) {
      myListener.stopListening((Language)module);
    }
  }
}
