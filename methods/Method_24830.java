public String getPdeCode(SketchInterval si){
  if (si == SketchInterval.BEFORE_START)   return "";
  int stop=Math.min(si.stopPdeOffset,pdeCode.length());
  int start=Math.min(si.startPdeOffset,stop);
  return pdeCode.substring(start,stop);
}
