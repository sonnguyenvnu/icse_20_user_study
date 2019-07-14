static public Library load(File folder){
  try {
    return new Library(folder);
  }
 catch (  Error err) {
    err.printStackTrace();
  }
catch (  Exception ex) {
    ex.printStackTrace();
  }
  return null;
}
