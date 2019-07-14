private static Component buildRow(ComponentContext c,int color,String key){
  return Row.create(c).child(buildCell(c,color,key + 0)).child(buildCell(c,color,key + 1)).child(buildCell(c,color,key + 2)).build();
}
