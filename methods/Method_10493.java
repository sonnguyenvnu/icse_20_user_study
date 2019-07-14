@Override public int hashCode(){
  return mY ^ ((mX << (Integer.SIZE / 2)) | (mX >>> (Integer.SIZE / 2)));
}
