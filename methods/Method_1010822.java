int register(StyleAttribute a){
  int index;
  if (ourFreeIndices.isEmpty()) {
    index=ourAttributes.size();
    ourAttributes.add(a);
  }
 else {
    index=ourFreeIndices.iterator().next();
    ourFreeIndices.remove(index);
    ourAttributes.set(index,a);
  }
  return index;
}
