private static boolean isTransientFailure(int type){
  return type > MmsSms.NO_ERROR && type < MmsSms.ERR_TYPE_GENERIC_PERMANENT;
}
