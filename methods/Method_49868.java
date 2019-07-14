public Bundle getCarrierConfigValues(){
  final Bundle bundle=new Bundle();
  final Iterator<Map.Entry<String,Object>> iter=mKeyValues.entrySet().iterator();
  while (iter.hasNext()) {
    final Map.Entry<String,Object> entry=iter.next();
    final String key=entry.getKey();
    final Object val=entry.getValue();
    Class<?> valueType=val != null ? val.getClass() : String.class;
    if (valueType == Integer.class) {
      bundle.putInt(key,(Integer)val);
    }
 else     if (valueType == Boolean.class) {
      bundle.putBoolean(key,(Boolean)val);
    }
 else     if (valueType == String.class) {
      bundle.putString(key,(String)val);
    }
  }
  return bundle;
}
