@Override public int hashCode(){
  return mQuality ^ (mIsOfGoodEnoughQuality ? 0x400000 : 0) ^ (mIsOfFullQuality ? 0x800000 : 0);
}
