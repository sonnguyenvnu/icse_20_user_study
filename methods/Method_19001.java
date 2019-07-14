public static String changeTypeToString(@Type int type){
switch (type) {
case INSERT:
    return "INSERT";
case UPDATE:
  return "UPDATE";
case DELETE:
return "DELETE";
case MOVE:
return "MOVE";
case INSERT_RANGE:
return "INSERT_RANGE";
case UPDATE_RANGE:
return "UPDATE_RANGE";
case DELETE_RANGE:
return "DELETE_RANGE";
default :
return "UNKNOW TYPE";
}
}
