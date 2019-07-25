public static SReferenceLinkAdapter deserialize(String s){
  if (s.startsWith(SReferenceLinkAdapterById.REF_PREFIX)) {
    return SReferenceLinkAdapterById.deserialize(s);
  }
 else   if (s.startsWith(InvalidReferenceLink.INVALID_PREFIX)) {
    return InvalidReferenceLink.deserialize(s);
  }
 else {
    throw new FormatException("Illegal ref type: " + s);
  }
}
