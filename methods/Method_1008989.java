/** 
 * <p>Reads a variant type from a byte array.</p>
 * @param src The byte array
 * @param offset The offset in the byte array where the variant starts
 * @param length The length of the variant including the variant type field
 * @param type The variant type to read
 * @param codepage The codepage to use for non-wide strings
 * @return A Java object that corresponds best to the variant field. Forexample, a VT_I4 is returned as a  {@link Long}, a VT_LPSTR as a {@link String}.
 * @exception ReadingNotSupportedException if a property is to be writtenwho's variant type HPSF does not yet support
 * @exception UnsupportedEncodingException if the specified codepage is notsupported.
 * @see Variant
 */
public static Object read(final byte[] src,final int offset,final int length,final long type,final int codepage) throws ReadingNotSupportedException, UnsupportedEncodingException {
  TypedPropertyValue typedPropertyValue=new TypedPropertyValue((int)type,null);
  int unpadded;
  try {
    unpadded=typedPropertyValue.readValue(src,offset);
  }
 catch (  UnsupportedOperationException exc) {
    int propLength=Math.min(length,src.length - offset);
    final byte[] v=new byte[propLength];
    System.arraycopy(src,offset,v,0,propLength);
    throw new ReadingNotSupportedException(type,v);
  }
switch ((int)type) {
case Variant.VT_EMPTY:
case Variant.VT_I4:
case Variant.VT_I8:
case Variant.VT_R8:
    return typedPropertyValue.getValue();
case Variant.VT_I2:
{
    return Integer.valueOf(((Short)typedPropertyValue.getValue()).intValue());
  }
case Variant.VT_FILETIME:
{
  Filetime filetime=(Filetime)typedPropertyValue.getValue();
  return Util.filetimeToDate((int)filetime.getHigh(),(int)filetime.getLow());
}
case Variant.VT_LPSTR:
{
CodePageString string=(CodePageString)typedPropertyValue.getValue();
return string.getJavaValue(codepage);
}
case Variant.VT_LPWSTR:
{
UnicodeString string=(UnicodeString)typedPropertyValue.getValue();
return string.toJavaString();
}
case Variant.VT_CF:
{
ClipboardData clipboardData=(ClipboardData)typedPropertyValue.getValue();
return clipboardData.toByteArray();
}
case Variant.VT_BOOL:
{
VariantBool bool=(VariantBool)typedPropertyValue.getValue();
return Boolean.valueOf(bool.getValue());
}
default :
{
final byte[] v=new byte[unpadded];
System.arraycopy(src,offset,v,0,unpadded);
throw new ReadingNotSupportedException(type,v);
}
}
}
