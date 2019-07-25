void unregister(StyleAttribute a){
  int index=ourAttributes.indexOf(a);
  ourAttributes.set(index,null);
  ourFreeIndices.add(index);
}
