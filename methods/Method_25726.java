private static String getMessage(Type fillingObjectType,Type arrayComponentType){
  String fillingTypeString=Signatures.prettyType(fillingObjectType);
  String arrayComponentTypeString=Signatures.prettyType(arrayComponentType);
  if (arrayComponentTypeString.equals(fillingTypeString)) {
    fillingTypeString=fillingObjectType.toString();
    arrayComponentTypeString=arrayComponentType.toString();
  }
  return "Calling Arrays.fill trying to put a " + fillingTypeString + " into an array of type " + arrayComponentTypeString;
}
