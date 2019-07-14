public void addDeny(String name){
  if (name == null || name.length() == 0) {
    return;
  }
  long hash=TypeUtils.fnv1a_64(name);
  if (Arrays.binarySearch(this.denyHashCodes,hash) >= 0) {
    return;
  }
  long[] hashCodes=new long[this.denyHashCodes.length + 1];
  hashCodes[hashCodes.length - 1]=hash;
  System.arraycopy(this.denyHashCodes,0,hashCodes,0,this.denyHashCodes.length);
  Arrays.sort(hashCodes);
  this.denyHashCodes=hashCodes;
}
