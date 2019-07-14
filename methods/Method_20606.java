private void onTakeView(final @NonNull ViewType view){
  Timber.d("onTakeView %s %s",this.toString(),view.toString());
  this.viewChange.onNext(view);
}
