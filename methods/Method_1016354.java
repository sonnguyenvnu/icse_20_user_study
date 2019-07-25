@Override public final String signature(){
  if ((!this.asc) && (!this.rfc1521compliant))   return "Bd";
  if ((!this.asc) && (this.rfc1521compliant))   return "bd";
  if ((this.asc) && (!this.rfc1521compliant))   return "Bu";
  if ((this.asc) && (this.rfc1521compliant))   return "bu";
  return null;
}
