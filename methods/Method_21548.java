@Override public String getChildType(){
  if (!this.isChildren())   return null;
  return this.getParamsAsMap().get("children").toString();
}
