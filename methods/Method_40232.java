@Nullable public static String readWholeFile(String filename){
  try {
    return new Scanner(new File(filename)).useDelimiter("PYSONAR2END").next();
  }
 catch (  FileNotFoundException e) {
    return null;
  }
}
