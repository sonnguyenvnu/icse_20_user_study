public JedisPool getJedisPoolFromSlot(int slot){
  return cache.getSlotPool(slot);
}
