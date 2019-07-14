static public String[] getImportSuggestions(ClassPath cp,String className){
  className=className.replace("[","\\[").replace("]","\\]");
  RegExpResourceFilter regf=new RegExpResourceFilter(Pattern.compile(".*"),Pattern.compile("(.*\\$)?" + className + "\\.class",Pattern.CASE_INSENSITIVE));
  String[] resources=cp.findResources("",regf);
  return Arrays.stream(resources).map(res -> res.substring(0,res.length() - 6)).map(res -> res.replace('/','.')).map(res -> res.replace('$','.')).sorted((o1,o2) -> {
    boolean o1StartsWithJava=o1.startsWith("java");
    boolean o2StartsWithJava=o2.startsWith("java");
    if (o1StartsWithJava != o2StartsWithJava) {
      if (o1StartsWithJava)       return -1;
      return 1;
    }
    return o1.compareTo(o2);
  }
).toArray(String[]::new);
}
