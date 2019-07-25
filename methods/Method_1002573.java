/** 
 * This method 'exposes' changes conveyed in the patch's meta commands to the main document. Contents of $set commands are moved to the node which contains $set command. Names of removed fields from $delete commands are moved to the nod which contains $delete command. The effect is that patch will resemble structurally document which it is supposed to modify. This allows application of projection to such patch to discover which changes relate to fields specified by that projection. Examples: $delete: ['x', 'y', 'z'] => x: true, y: true, z: true $set: {x: 10, y: {z: 'yeey'}, t: [10]} => x: 10, y: {z: 'yeey'}, t: [10] This method works in-place, meaning that the doc may be mutated.
 */
private static void expose(DataMap doc){
  Set<String> fields=doc.keySet();
  DataMap toAdd=new DataMap();
  for (  String f : fields) {
    Object v=doc.get(f);
    if (f.equals(PatchConstants.DELETE_COMMAND)) {
      for (      Object removedFields : (DataList)v) {
        toAdd.put((String)removedFields,true);
      }
    }
 else     if (f.equals(PatchConstants.SET_COMMAND)) {
      toAdd.putAll((DataMap)v);
    }
 else     if (v instanceof DataMap) {
      expose((DataMap)v);
    }
  }
  doc.putAll(toAdd);
}
