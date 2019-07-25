private static double entropy(double pc,double pnc){
  assert (Math.abs((pc + pnc) - 1) < 0.0001) : "pc=" + pc + " pnc=" + pnc;
  if (pc == 0 || pnc == 0)   return (float)0;
 else {
    float ret=(float)(-pc * Math.log(pc) / log2 - pnc * Math.log(pnc) / log2);
    assert (ret >= 0) : "pc=" + pc + " pnc=" + pnc;
    return ret;
  }
}
