private static String kindName(Binding.Kind kind){
  if (kind == Binding.Kind.CLASS_METHOD) {
    return "method";
  }
 else {
    return kind.toString().toLowerCase();
  }
}
