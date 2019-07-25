@Override public void register(MembersInjector<? super T> membersInjector){
  if (!valid) {
    throw new IllegalStateException("Encounters may not be used after hear() returns.");
  }
  if (membersInjectors == null) {
    membersInjectors=new ArrayList<>();
  }
  membersInjectors.add(membersInjector);
}
