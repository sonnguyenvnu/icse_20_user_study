private void fillStack(@Nonnull String[] arr,@Nonnull Stack<String> stack){
  for (  String str : arr) {
    if ("/".equals(str)) {
      continue;
    }
    if ("..".equals(str)) {
      if (!stack.isEmpty()) {
        stack.pop();
      }
    }
 else     if (!".".equals(str) && !str.isEmpty()) {
      stack.push(str);
    }
  }
}
