private static PropertyKey[] getKeysOfRecords(RecordEntry[] record){
  final PropertyKey[] keys=new PropertyKey[record.length];
  for (int i=0; i < record.length; i++)   keys[i]=record[i].key;
  return keys;
}
