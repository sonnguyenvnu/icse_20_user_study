private static int getValuePos(long limitAndValuePos){
  return (int)(limitAndValuePos & Integer.MAX_VALUE);
}
