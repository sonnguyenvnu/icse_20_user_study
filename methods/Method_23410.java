private int filter_bilinear(){
  fracU=sX & PREC_MAXVAL;
  ifU=PREC_MAXVAL - fracU + 1;
  ul=(ifU * ifV) >> PRECISIONB;
  ll=ifU - ul;
  ur=ifV - ul;
  lr=PREC_MAXVAL + 1 - ul - ll - ur;
  u1=(sX >> PRECISIONB);
  u2=min(u1 + 1,iw1);
  cUL=srcBuffer[v1 + u1];
  cUR=srcBuffer[v1 + u2];
  cLL=srcBuffer[v2 + u1];
  cLR=srcBuffer[v2 + u2];
  r=((ul * ((cUL & RED_MASK) >> 16) + ll * ((cLL & RED_MASK) >> 16) + ur * ((cUR & RED_MASK) >> 16) + lr * ((cLR & RED_MASK) >> 16)) << PREC_RED_SHIFT) & RED_MASK;
  g=((ul * (cUL & GREEN_MASK) + ll * (cLL & GREEN_MASK) + ur * (cUR & GREEN_MASK) + lr * (cLR & GREEN_MASK)) >>> PRECISIONB) & GREEN_MASK;
  b=(ul * (cUL & BLUE_MASK) + ll * (cLL & BLUE_MASK) + ur * (cUR & BLUE_MASK) + lr * (cLR & BLUE_MASK)) >>> PRECISIONB;
  a=((ul * ((cUL & ALPHA_MASK) >>> 24) + ll * ((cLL & ALPHA_MASK) >>> 24) + ur * ((cUR & ALPHA_MASK) >>> 24) + lr * ((cLR & ALPHA_MASK) >>> 24)) << PREC_ALPHA_SHIFT) & ALPHA_MASK;
  return a | r | g | b;
}
