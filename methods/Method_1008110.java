/** 
 * parses with tika, throwing any exception hit while parsing the document
 */
static String parse(final byte content[],final Metadata metadata,final int limit) throws TikaException, IOException {
  SpecialPermission.check();
  try {
    return AccessController.doPrivileged((PrivilegedExceptionAction<String>)() -> TIKA_INSTANCE.parseToString(new ByteArrayInputStream(content),metadata,limit),RESTRICTED_CONTEXT);
  }
 catch (  PrivilegedActionException e) {
    Throwable cause=e.getCause();
    if (cause instanceof TikaException) {
      throw (TikaException)cause;
    }
 else     if (cause instanceof IOException) {
      throw (IOException)cause;
    }
 else {
      throw new AssertionError(cause);
    }
  }
}
