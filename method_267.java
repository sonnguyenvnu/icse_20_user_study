public synchronized Versioned<Long> _XXXXX_(long txId){
  return new Versioned<Long>(Math.max(txId,get()),version);
}