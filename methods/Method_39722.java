private void maybeUseType(final String typeName){
  if (!isTopLevelType()) {
    return;
  }
  char type;
  String bytecodeName;
  if (visitingArray) {
    type='[';
    int arrayCount=StringUtil.count(typeName,'[');
    String arrayDepth=StringUtil.repeat('[',arrayCount);
    int ndx=typeName.indexOf('[');
    bytecodeName=typeName.substring(0,ndx);
    char arrayType=AsmUtil.typeNameToOpcode(bytecodeName);
    if (arrayType != 'L') {
      bytecodeName=String.valueOf(arrayType);
    }
 else {
      bytecodeName=resolveBytecodeName(bytecodeName);
    }
    bytecodeName=arrayDepth + bytecodeName;
  }
 else {
    type=AsmUtil.typeNameToOpcode(typeName);
    if (type != 'L') {
      bytecodeName=String.valueOf(type);
    }
 else {
      bytecodeName=resolveBytecodeName(typeName);
    }
  }
  final TypeInfoImpl typeInfo=new TypeInfoImpl(type,typeName,bytecodeName,resolveRawTypeName(bytecodeName));
  if (visitingArgument) {
    if (type == 'V') {
      throw new ProxettaException("Method argument can't be void");
    }
    arguments.add(typeInfo);
    argumentsCount++;
    argumentsOffset.append(argumentsWords + 1);
    if ((type == 'D') || (type == 'J')) {
      argumentsWords+=2;
    }
 else {
      argumentsWords++;
    }
  }
 else   if (visitingReturnType) {
    returnType=typeInfo;
  }
  visitingReturnType=false;
  visitingArgument=false;
  visitingArray=false;
}
