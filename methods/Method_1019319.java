/** 
 * {@inheritDoc}
 */
@Override public KTypeVTypeHashMap<KType,VType> clone(){
  try {
    @SuppressWarnings("unchecked") KTypeVTypeHashMap<KType,VType> cloned=(KTypeVTypeHashMap<KType,VType>)super.clone();
    cloned.keys=keys.clone();
    cloned.values=values.clone();
    cloned.hasEmptyKey=cloned.hasEmptyKey;
    cloned.orderMixer=orderMixer.clone();
    return cloned;
  }
 catch (  CloneNotSupportedException e) {
    throw new RuntimeException(e);
  }
}
