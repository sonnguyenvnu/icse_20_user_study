protected static DataFlavor[] getDefaultFlavors(){
  try {
    return new DataFlavor[]{new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class=java.lang.String"),DataFlavor.stringFlavor};
  }
 catch (  ClassNotFoundException cle) {
    InternalError ie=new InternalError("error initializing StringTransferable");
    ie.initCause(cle);
    throw ie;
  }
}
