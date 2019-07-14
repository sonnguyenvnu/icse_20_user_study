private static String renderList(List<?> list){
  return Joiner.on("\n\n").join(from(list).transform(toStringFunction()));
}
