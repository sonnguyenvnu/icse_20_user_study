public static boolean equals(Rope a,Rope b){
  if (a.size() != b.size()) {
    return false;
  }
  if (a.size() == 0) {
    return true;
  }
  return compare(a.bytes(),b.bytes()) == 0;
}
