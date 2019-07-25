@Override public void attach(SRepository repository){
  super.attach(repository);
  getSource().addListener(this);
}
