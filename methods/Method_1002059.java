public static NameValue header(final String name,final String value){
  if (!isAllASCII(name) || !isAllASCII(value))   throw new IllegalArgumentException("Header " + name + " must be ASCII only! Read http://stackoverflow.com/a/4410331");
  return new NameValue(name,value);
}
