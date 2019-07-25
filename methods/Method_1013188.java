public void Merge(Changed c){
  PcalDebug.Assert(count.length == c.count.length);
  for (int i=0; i < count.length; i++)   count[i]=(count[i] > c.count[i]) ? count[i] : c.count[i];
}
