public static byte createMapb(int pos){
  assert pos >= 0 && pos < 8;
  return (byte)(1 << pos);
}
