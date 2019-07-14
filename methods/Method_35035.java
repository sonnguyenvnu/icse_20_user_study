@Nonnull public String simplifyPath(@Nonnull String path){
  if (ifInvalidPATH(path)) {
    return "";
  }
  String delim="[/]+";
  String[] arr=path.split(delim);
  Stack<String> stack=new Stack<>();
  fillStack(arr,stack);
  if (emptyStack(stack)) {
    return "/";
  }
  StringBuilder sb=new StringBuilder();
  for (  String str : stack) {
    sb.append("/").append(str);
  }
  return sb.toString();
}
