protected static DataFlavor[] getDefaultFlavors(){
  try {
    return new DataFlavor[]{new DataFlavor("text/plain;class=java.lang.String"),new DataFlavor("text/plain;class=java.io.Reader"),new DataFlavor("text/plain;charset=unicode;class=java.io.InputStream")};
  }
 catch (  ClassNotFoundException cle) {
    InternalError ie=new InternalError("error initializing PlainTextTransferable");
    ie.initCause(cle);
    throw ie;
  }
}
