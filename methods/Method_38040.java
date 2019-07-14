public int getInteger(final int index){
  try {
    return statement.getInt(index);
  }
 catch (  SQLException sex) {
    throw newGetParamError(index,sex);
  }
}
