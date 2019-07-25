@Override public void attach(SRepository repository){
  getSource().addListener(this);
  super.attach(repository);
}
