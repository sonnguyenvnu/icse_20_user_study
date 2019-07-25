private static Keyword readable(int status){
switch (status) {
case 0:
    return K_BY_SERVER;
case -1:
  return K_CLIENT_CLOSED;
case 1000:
return K_WS_1000;
case 1001:
return K_WS_1001;
case 1002:
return K_WS_1002;
case 1003:
return K_WS_1003;
case 1005:
return K_WS_1005;
case 1006:
return K_WS_1006;
case 1007:
return K_WS_1007;
case 1008:
return K_WS_1008;
case 1009:
return K_WS_1009;
case 1010:
return K_WS_1010;
case 1011:
return K_WS_1011;
case 1015:
return K_WS_1015;
default :
return K_UNKNOWN;
}
}
