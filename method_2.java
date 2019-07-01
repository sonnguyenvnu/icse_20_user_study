void _XXXXX_(){
  if (received != -1 || sent == -1)   throw new IllegalStateException();
  received=sent - 1;
  latch.countDown();
}