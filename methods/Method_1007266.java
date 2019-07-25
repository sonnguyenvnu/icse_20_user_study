public Subroutine[] scan(MethodInfo method) throws BadBytecode {
  CodeAttribute code=method.getCodeAttribute();
  CodeIterator iter=code.iterator();
  subroutines=new Subroutine[code.getCodeLength()];
  subTable.clear();
  done.clear();
  scan(0,iter,null);
  ExceptionTable exceptions=code.getExceptionTable();
  for (int i=0; i < exceptions.size(); i++) {
    int handler=exceptions.handlerPc(i);
    scan(handler,iter,subroutines[exceptions.startPc(i)]);
  }
  return subroutines;
}
