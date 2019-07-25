@Override public Iterator<Mapper> iterator(){
  List<Mapper> mappers=new ArrayList<>(parentIdFields);
  mappers.add(uniqueFieldMapper);
  return mappers.iterator();
}
