public void put(Object src,Object dest,String mapId){
  int destId=System.identityHashCode(dest);
  Map<Integer,MapIdField> mappedTo=mappedFields.get(src);
  if (mappedTo == null) {
    mappedTo=new HashMap<>();
    mappedFields.put(src,mappedTo);
  }
  MapIdField destMapIdField=mappedTo.get(destId);
  if (destMapIdField == null) {
    destMapIdField=new MapIdField();
    mappedTo.put(destId,destMapIdField);
  }
  if (!destMapIdField.containsMapId(mapId)) {
    destMapIdField.put(mapId,dest);
  }
}
