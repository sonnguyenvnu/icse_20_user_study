@Override public final String signature(){
  if (!this.asc)   return "nd";
  if (this.asc)   return "nu";
  return null;
}
