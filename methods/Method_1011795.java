public boolean includes(MfDate arg){
  return !(arg.before(myStart)) && !(arg.after(myEnd));
}
