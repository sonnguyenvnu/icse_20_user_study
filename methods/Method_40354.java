private mod invokeANTLR(String filename,String contents){
  CharStream text=new ANTLRStringStream(contents);
  return invokeANTLR(text,filename);
}
