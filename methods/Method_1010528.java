@Override public void init(){
  myRepository.getModelAccess().runWriteAction(() -> myRepository.addRepositoryListener(myListener));
}
