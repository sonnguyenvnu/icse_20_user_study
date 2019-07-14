private mod invokeANTLR(String filename){
  CharStream text=null;
  try {
    text=new ANTLRFileStream(filename);
  }
 catch (  IOException iox) {
    fine(filename + ": " + iox);
    return null;
  }
  return invokeANTLR(text,filename);
}
