public void clear(){
  if (mIsSync) {
synchronized (this) {
      while (acquire() != null) {
      }
    }
  }
 else {
    while (acquire() != null) {
    }
  }
}
