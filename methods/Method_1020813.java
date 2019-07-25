public static String join(Collection collection){
  if (collection.size() == 0)   return null;
  Iterator ite=collection.iterator();
  StringBuffer stringBuffer=new StringBuffer();
  while (ite.hasNext()) {
    stringBuffer.append(ite.next());
  }
  return stringBuffer.toString();
}
