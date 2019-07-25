private void collect(File folder,String prefix,ArrayList<String> result){
  for (  File file : folder.listFiles()) {
    if (file.isDirectory()) {
      collect(file,prefix + SEPARATOR_CHAR + file.getName(),result);
    }
 else {
      result.add(prefix + SEPARATOR_CHAR + file.getName());
    }
  }
}
