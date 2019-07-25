public static TreeInfo walk(String start,String regex){
  return recuresDirs(new File(start),regex);
}
