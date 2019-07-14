private static boolean isTrueFeature(boolean isPartial,Configuration oracleConfiguration,int action){
  boolean isTrueFeature=true;
  if (isPartial && action >= 3) {
    if (!oracleConfiguration.state.hasHead(oracleConfiguration.state.stackTop()) || !oracleConfiguration.state.hasHead(oracleConfiguration.state.bufferHead()))     isTrueFeature=false;
  }
 else   if (isPartial && action == 0) {
    if (!oracleConfiguration.state.hasHead(oracleConfiguration.state.bufferHead()))     isTrueFeature=false;
  }
 else   if (isPartial && action == 1) {
    if (!oracleConfiguration.state.hasHead(oracleConfiguration.state.stackTop()))     isTrueFeature=false;
  }
  return isTrueFeature;
}
