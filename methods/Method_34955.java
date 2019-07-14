private void initStore(){
  if (this.persistedStore == null) {
    this.persistedStore=((SampleApp)getApplicationContext()).getPersistedStore();
  }
}
