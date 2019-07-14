public boolean isSuperCall(){
  return func instanceof Name && ((Name)func).id.equals("super");
}
