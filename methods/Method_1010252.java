private void update(){
  myRepository.getModelAccess().checkReadAccess();
  if (isChanged()) {
    recountStatus();
  }
}
