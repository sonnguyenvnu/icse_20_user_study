public static Header[] copy(final Header[] headers){
  if (headers == null)   return new Header[0];
  Header[] retval=new Header[headers.length];
  System.arraycopy(headers,0,retval,0,headers.length);
  return retval;
}
