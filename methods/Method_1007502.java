public void idledown(){
  while (!Thread.interrupted())   if (waitIdle(100))   return;
}
