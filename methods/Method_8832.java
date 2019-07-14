public int entitiesCount(){
  int count=0;
  for (int index=0; index < getChildCount(); index++) {
    View view=getChildAt(index);
    if (!(view instanceof EntityView)) {
      continue;
    }
    count++;
  }
  return count;
}
