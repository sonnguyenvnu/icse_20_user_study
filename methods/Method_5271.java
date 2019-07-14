/** 
 * Removes unnecessary  {@link SchemeData}s with null  {@link SchemeData#data}.
 */
private static void filterRedundantIncompleteSchemeDatas(ArrayList<SchemeData> schemeDatas){
  for (int i=schemeDatas.size() - 1; i >= 0; i--) {
    SchemeData schemeData=schemeDatas.get(i);
    if (!schemeData.hasData()) {
      for (int j=0; j < schemeDatas.size(); j++) {
        if (schemeDatas.get(j).canReplace(schemeData)) {
          schemeDatas.remove(i);
          break;
        }
      }
    }
  }
}
