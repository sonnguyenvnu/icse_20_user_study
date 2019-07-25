private static void advance(final BytesRef ref,final int length){
  assert ref.length >= length : " ref.length: " + ref.length + " length: " + length;
  assert ref.offset + length < ref.bytes.length || (ref.offset + length == ref.bytes.length && ref.length - length == 0) : "offset: " + ref.offset + " ref.bytes.length: " + ref.bytes.length + " length: " + length + " ref.length: " + ref.length;
  ref.length-=length;
  ref.offset+=length;
}
