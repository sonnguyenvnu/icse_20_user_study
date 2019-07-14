@Override public String toString(){
  return action + (StringUtils.hasText(describe) ? "(" + describe + ")" : "");
}
