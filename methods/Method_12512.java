public void setLifetime(Duration statusLifetime){
  this.intervalCheck.setMinRetention(statusLifetime);
}
