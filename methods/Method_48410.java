private int require(int position,int size){
  int base=position + offset;
  if (position < 0)   throw new ArrayIndexOutOfBoundsException("Position [" + position + "] must be nonnegative");
  if (base + size > limit)   throw new ArrayIndexOutOfBoundsException("Required size [" + size + "] " + "exceeds actual remaining size [" + (limit - base) + "]");
  assert base + size <= limit;
  return base;
}
