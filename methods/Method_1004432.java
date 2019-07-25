public int entries(){
  try {
    return (int)(validate() / Long.BYTES);
  }
 catch (  Exception e) {
    return 0;
  }
}
