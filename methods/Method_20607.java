private void dropView(){
  Timber.d("dropView %s",this.toString());
  this.viewChange.onNext(null);
}
