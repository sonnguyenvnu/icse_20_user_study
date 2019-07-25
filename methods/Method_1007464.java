protected boolean resolved(TimerService ts){
  if (count.get() > 0)   return false;
  return ts.isEmptyLazy(exes[0]);
}
