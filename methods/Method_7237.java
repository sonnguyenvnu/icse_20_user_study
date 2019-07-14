public static int idealByteArraySize(int need){
  for (int i=4; i < 32; i++)   if (need <= (1 << i) - 12)   return (1 << i) - 12;
  return need;
}
