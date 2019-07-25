private static HashMap<String,SettableBeanProperty> _copy(HashMap<String,SettableBeanProperty> src){
  return (src == null) ? null : new HashMap<String,SettableBeanProperty>(src);
}
