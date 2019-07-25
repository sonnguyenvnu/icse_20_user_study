@Override public Level0Iterator iterator(){
  return new Level0Iterator(tableCache,files,internalKeyComparator);
}
