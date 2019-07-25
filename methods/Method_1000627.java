public CharSequence render(Context context){
  StringBuilder sb=new StringBuilder();
  for (  SegmentNode node : nodes) {
    Object val=node.isKey() ? context.get(node.getValue()) : node.getValue();
    if (null == val)     continue;
    if (val instanceof Collection<?>) {
      for (      Object obj : (Collection<?>)val) {
        sb.append(obj);
      }
    }
 else {
      sb.append(val);
    }
  }
  return sb;
}
