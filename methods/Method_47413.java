private void onFound(ComputerParcelable computer){
  mResults.add(computer);
synchronized (this.mLock) {
    if (this.observer != null) {
      this.observer.computerFound(computer);
    }
  }
}
