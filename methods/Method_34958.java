private void initStore(){
  if (this.nonPersistedStore == null) {
    this.nonPersistedStore=((SampleApp)getApplicationContext()).getNonPersistedStore();
  }
}
