private static String[] append(String[] paths,String newPath){
  if (contains(paths,newPath)) {
    return paths;
  }
  final int newPathsCount=1 + (paths != null ? paths.length : 0);
  final String[] newPaths=new String[newPathsCount];
  if (paths != null) {
    System.arraycopy(paths,0,newPaths,0,paths.length);
  }
  newPaths[newPathsCount - 1]=newPath;
  return newPaths;
}
