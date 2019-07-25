public void add(String name){
  path[index++]=name;
  if (index == path.length) {
    String[] newPath=new String[path.length + 10];
    System.arraycopy(path,0,newPath,0,path.length);
    path=newPath;
  }
}
