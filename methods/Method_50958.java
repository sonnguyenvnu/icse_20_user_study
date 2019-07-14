private void popType(){
switch (typeType) {
case NO_TYPE:
    break;
case FIELD_TYPE:
  fieldType=getType();
break;
case RETURN_TYPE:
returnType=getType();
break;
case PARAMETER_TYPE:
parameterTypes.add(getType());
break;
default :
throw new RuntimeException("Unknown type type: " + typeType);
}
typeType=NO_TYPE;
type=null;
arrayDimensions=0;
}
