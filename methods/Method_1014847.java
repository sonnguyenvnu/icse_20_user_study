@Override public String evaluate(WalkedPath walkedPath){
switch (arrayPathType) {
case AUTO_EXPAND:
    return canonicalForm;
case EXPLICIT_INDEX:
  return arrayIndex;
case HASH:
MatchedElement element=walkedPath.elementFromEnd(ref.getPathIndex()).getMatchedElement();
Integer index=element.getHashCount();
return index.toString();
case TRANSPOSE:
String key=transposePathElement.evaluate(walkedPath);
return verifyStringIsNonNegativeInteger(key);
case REFERENCE:
MatchedElement lpe=walkedPath.elementFromEnd(ref.getPathIndex()).getMatchedElement();
String keyPart;
if (ref instanceof PathAndGroupReference) {
keyPart=lpe.getSubKeyRef(((PathAndGroupReference)ref).getKeyGroup());
}
 else {
keyPart=lpe.getSubKeyRef(0);
}
return verifyStringIsNonNegativeInteger(keyPart);
default :
throw new IllegalStateException("ArrayPathType enum added two without updating this switch statement.");
}
}
