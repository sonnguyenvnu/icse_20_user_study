/** 
 * This method trims doc (1st arg) according to projected (2nd arg), which may have been modified by projection. This method works in-place, meaning that the doc may be mutated.
 */
private static void trim(DataMap doc,DataMap projected){
  DataMap toAddDoc=new DataMap();
  Set<String> fields=doc.keySet();
  List<String> toRemoveDoc=new ArrayList<String>(fields.size());
  for (  String f : fields) {
    Object v=doc.get(f);
    if (f.equals(PatchConstants.DELETE_COMMAND)) {
      DataList deletedFields=(DataList)v;
      DataList filteredDeleteFields=new DataList();
      for (      Object patchDeleteField : deletedFields) {
        if (projected.containsKey(patchDeleteField)) {
          filteredDeleteFields.add(patchDeleteField);
        }
      }
      toRemoveDoc.add(f);
      if (!filteredDeleteFields.isEmpty()) {
        toAddDoc.put(PatchConstants.DELETE_COMMAND,filteredDeleteFields);
      }
    }
 else     if (f.equals(PatchConstants.SET_COMMAND)) {
      DataMap setFields=(DataMap)v;
      Set<String> setFieldNames=setFields.keySet();
      List<String> toRemove=new LinkedList<String>();
      DataMap filteredSetFields=new DataMap();
      for (      String setFieldName : setFieldNames) {
        if (projected.containsKey(setFieldName)) {
          filteredSetFields.put(setFieldName,projected.get(setFieldName));
        }
        toRemove.add(setFieldName);
      }
      for (      String fieldToRemove : toRemove) {
        setFields.remove(fieldToRemove);
        if (filteredSetFields.containsKey(fieldToRemove)) {
          setFields.put(fieldToRemove,filteredSetFields.get(fieldToRemove));
        }
      }
      if (setFields.isEmpty()) {
        toRemoveDoc.add(f);
      }
    }
 else     if (v instanceof DataMap) {
      if (projected.containsKey(f)) {
        trim((DataMap)v,(DataMap)projected.get(f));
      }
 else {
        toRemoveDoc.add(f);
      }
    }
  }
  for (  String f : toRemoveDoc) {
    doc.remove(f);
  }
  for (  String f : toAddDoc.keySet()) {
    doc.put(f,toAddDoc.get(f));
  }
}
