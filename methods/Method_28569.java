public void setEnableNestedScrolling(boolean enableNestedScrolling){
  if (this.enableNestedScrolling != enableNestedScrolling) {
    setNestedScrollingEnabled(enableNestedScrolling);
    this.enableNestedScrolling=enableNestedScrolling;
  }
}
