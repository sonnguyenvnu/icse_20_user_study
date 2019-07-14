private String[] evaluate(String label,Map<Integer,Integer> map){
  long base=meter.measureDeep(map);
  map.putAll(workingSet);
  long populated=meter.measureDeep(map);
  long entryOverhead=2 * FUZZY_SIZE * meter.measureDeep(workingSet.keySet().iterator().next());
  long perEntry=LongMath.divide(populated - entryOverhead - base,FUZZY_SIZE,RoundingMode.HALF_EVEN);
  perEntry+=((perEntry & 1) == 0) ? 0 : 1;
  long aligned=((perEntry % 8) == 0) ? perEntry : ((1 + perEntry / 8) * 8);
  return new String[]{label,String.format("%,d bytes",base),String.format("%,d bytes (%,d aligned)",perEntry,aligned)};
}
