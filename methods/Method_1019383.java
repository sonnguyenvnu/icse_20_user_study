@Override public void found(String id){
  try {
    int idAsInt=Integer.parseInt(id);
    if (idAsInt > ID) {
      ID=idAsInt;
    }
  }
 catch (  NumberFormatException e) {
  }
}
