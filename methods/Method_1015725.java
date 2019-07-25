protected int count(boolean missing){
  int retval=0;
  long tmp_hd=hd, tmp_hr=hr.get();
  for (long i=tmp_hd + 1; i <= tmp_hr; i++) {
    int index=index(i);
    T element=buf.get(index);
    if (missing && element == null)     retval++;
    if (!missing && element != null)     retval++;
  }
  return retval;
}
