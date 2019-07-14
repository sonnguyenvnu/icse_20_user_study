private int continuousId(int id){
  if (id <= bId) {
    return id / y_.size();
  }
 else {
    return id / y_.size() - y_.size() + 1;
  }
}
