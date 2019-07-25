public String dump(){
synchronized (this) {
    return "" + id + "(running=" + running + ", pr=" + pauseReason + ")";
  }
}
