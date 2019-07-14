public void addAccept(String name){
  if (name == null || name.length() == 0) {
    return;
  }
  long hash=TypeUtils.fnv1a_64(name);
  if (Arrays.binarySearch(this.acceptHashCodes,hash) >= 0) {
    return;
  }
  long[] hashCodes=new long[this.acceptHashCodes.length + 1];
  hashCodes[hashCodes.length - 1]=hash;
  System.arraycopy(this.acceptHashCodes,0,hashCodes,0,this.acceptHashCodes.length);
  Arrays.sort(hashCodes);
  this.acceptHashCodes=hashCodes;
}
