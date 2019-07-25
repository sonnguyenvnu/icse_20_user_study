public long now(){
  return statsEnabled ? System.nanoTime() : -1;
}
