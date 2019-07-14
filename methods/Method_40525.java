@Override public String toString(){
  return "<Scope:" + getScopeType() + ":" + path + ":" + (table == null ? "{}" : table.keySet()) + ">";
}
