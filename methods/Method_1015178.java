@Override public ISet<K> keys(){
  return new LinearSet<K>((LinearMap<K,Void>)this);
}
