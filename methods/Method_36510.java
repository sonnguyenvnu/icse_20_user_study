@Override public String toString(){
  return this.getInterfaceType().getName() + (StringUtils.hasText(uniqueId) ? ":" + uniqueId : "");
}
