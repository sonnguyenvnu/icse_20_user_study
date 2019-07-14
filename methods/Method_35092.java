void setBackstack(@NonNull List<RouterTransaction> backstack){
  this.backstack.clear();
  for (  RouterTransaction transaction : backstack) {
    this.backstack.push(transaction);
  }
}
