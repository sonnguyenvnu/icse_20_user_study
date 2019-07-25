private static RegisterSpecList concatenate(Local<?> first,Local<?>[] rest){
  int offset=(first != null) ? 1 : 0;
  RegisterSpecList result=new RegisterSpecList(offset + rest.length);
  if (first != null) {
    result.set(0,first.spec());
  }
  for (int i=0; i < rest.length; i++) {
    result.set(i + offset,rest[i].spec());
  }
  return result;
}
