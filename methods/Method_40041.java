@Nullable public String makeQname(@NotNull List<Name> names){
  if (names.isEmpty()) {
    return "";
  }
  String ret="";
  for (int i=0; i < names.size() - 1; i++) {
    ret+=names.get(i).id + ".";
  }
  ret+=names.get(names.size() - 1).id;
  return ret;
}
