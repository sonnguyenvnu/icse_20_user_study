private Object getFormatter(){
  Object f=iFormatter;
  if (f == null) {
    if (iElementPairs.size() == 2) {
      Object printer=iElementPairs.get(0);
      Object parser=iElementPairs.get(1);
      if (printer != null) {
        if (printer == parser || parser == null) {
          f=printer;
        }
      }
 else {
        f=parser;
      }
    }
    if (f == null) {
      f=new Composite(iElementPairs);
    }
    iFormatter=f;
  }
  return f;
}
