public KeyStore loadStore(){
  InputStream instream=null;
  try {
    KeyStore trustStore=KeyStore.getInstance(KeyStore.getDefaultType());
    instream=createInputStream();
    trustStore.load(instream,password.toCharArray());
    return trustStore;
  }
 catch (  Exception e) {
    return throwUnchecked(e,KeyStore.class);
  }
 finally {
    if (instream != null) {
      try {
        instream.close();
      }
 catch (      IOException ioe) {
        throwUnchecked(ioe);
      }
    }
  }
}
