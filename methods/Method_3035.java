@Override protected EnumItem<E> createValue(String[] params){
  Map.Entry<String,Map.Entry<String,Integer>[]> args=EnumItem.create(params);
  EnumItem<E> nrEnumItem=new EnumItem<E>();
  for (  Map.Entry<String,Integer> e : args.getValue()) {
    nrEnumItem.labelMap.put(valueOf(e.getKey()),e.getValue());
  }
  return nrEnumItem;
}
