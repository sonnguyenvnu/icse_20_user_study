private static void constructIndexCover(Object[] indexValues,int position,IndexField[] fields,Condition<JanusGraphElement> condition,List<Object[]> indexCovers,Set<Condition> coveredClauses){
  if (position >= fields.length) {
    indexCovers.add(indexValues);
  }
 else {
    final IndexField field=fields[position];
    final Map.Entry<Condition,Collection<Object>> equalCon=getEqualityConditionValues(condition,field.getFieldKey());
    if (equalCon != null) {
      coveredClauses.add(equalCon.getKey());
      assert equalCon.getValue().size() > 0;
      for (      final Object value : equalCon.getValue()) {
        final Object[] newValues=Arrays.copyOf(indexValues,fields.length);
        newValues[position]=value;
        constructIndexCover(newValues,position + 1,fields,condition,indexCovers,coveredClauses);
      }
    }
  }
}
