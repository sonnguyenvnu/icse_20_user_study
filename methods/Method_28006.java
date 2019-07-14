@NonNull public String getRef(){
  return !InputHelper.isEmpty(ref) ? ref : "master";
}
