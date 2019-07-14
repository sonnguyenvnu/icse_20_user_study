public Object[] parseParamArg(Class[] classTypes,String params){
  String[] paramList=params.split("&");
  if (classTypes.length != paramList.length) {
    throw new SofaRpcException(RpcErrorType.SERVER_DESERIALIZE,"The number of parameter is wrong.");
  }
  Object[] resultList=new Object[paramList.length];
  for (int i=0; i < classTypes.length; i++) {
    Class cl=classTypes[i];
    String value=paramList[i].substring(paramList[i].indexOf('=') + 1).trim();
    if (String.class.equals(cl)) {
      try {
        resultList[i]=URLDecoder.decode(value,"UTF-8");
      }
 catch (      UnsupportedEncodingException e) {
        resultList[i]=value;
      }
    }
 else     if (boolean.class.equals(cl) || Boolean.class.equals(cl)) {
      resultList[i]=Boolean.parseBoolean(value);
    }
 else     if (byte.class.equals(cl) || Byte.class.equals(cl)) {
      resultList[i]=Byte.decode(value);
    }
 else     if (short.class.equals(cl) || Short.class.equals(cl)) {
      resultList[i]=Short.decode(value);
    }
 else     if (char.class.equals(cl) || Character.class.equals(cl)) {
      resultList[i]=Character.valueOf(value.charAt(0));
    }
 else     if (int.class.equals(cl) || Integer.class.equals(cl)) {
      resultList[i]=Integer.decode(value);
    }
 else     if (long.class.equals(cl) || Long.class.equals(cl)) {
      resultList[i]=Long.decode(value);
    }
 else     if (float.class.equals(cl) || Float.class.equals(cl)) {
      resultList[i]=Float.valueOf(value);
    }
 else     if (double.class.equals(cl) || Double.class.equals(cl)) {
      resultList[i]=Double.valueOf(value);
    }
 else {
      throw new UnsupportedOperationException();
    }
  }
  return resultList;
}
