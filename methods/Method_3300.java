public int eval(){
  int err=0;
  for (int i=0; i < x_.size(); i++) {
    if (!answer_.get(i).equals(result_.get(i))) {
      err++;
    }
  }
  return err;
}
