void mcsrch(int size,double[] x,double f,double[] g,double[] s,int startOffset,double[] stp,int[] info,int[] nfev,double[] wa){
  double p5=0.5;
  double p66=0.66;
  double xtrapf=4.0;
  int maxfev=20;
  if (info[0] != -1) {
    infoc=1;
    if (size <= 0 || stp[0] <= 0.0) {
      return;
    }
    dginit=ddot_(size,g,0,s,startOffset);
    if (dginit >= 0.0)     return;
    brackt=false;
    stage1=true;
    nfev[0]=0;
    finit=f;
    dgtest=ftol * dginit;
    width=lb3_1_stpmax - lb3_1_stpmin;
    width1=width / p5;
    for (int j=0; j < size; ++j) {
      wa[j]=x[j];
    }
    stx=0.0;
    fx=finit;
    dgx=dginit;
    sty=0.0;
    fy=finit;
    dgy=dginit;
  }
  boolean firstLoop=true;
  while (true) {
    if (!firstLoop || (firstLoop && info[0] != -1)) {
      if (brackt) {
        stmin=Math.min(stx,sty);
        stmax=Math.max(stx,sty);
      }
 else {
        stmin=stx;
        stmax=stp[0] + xtrapf * (stp[0] - stx);
      }
      stp[0]=Math.max(stp[0],lb3_1_stpmin);
      stp[0]=Math.min(stp[0],lb3_1_stpmax);
      if ((brackt && ((stp[0] <= stmin || stp[0] >= stmax) || nfev[0] >= maxfev - 1 || infoc == 0)) || (brackt && (stmax - stmin <= xtol * stmax))) {
        stp[0]=stx;
      }
      for (int j=0; j < size; ++j) {
        x[j]=wa[j] + stp[0] * s[startOffset + j];
      }
      info[0]=-1;
      return;
    }
    info[0]=0;
    ++(nfev[0]);
    double dg=ddot_(size,g,0,s,startOffset);
    double ftest1=finit + stp[0] * dgtest;
    if (brackt && ((stp[0] <= stmin || stp[0] >= stmax) || infoc == 0)) {
      info[0]=6;
    }
    if (stp[0] == lb3_1_stpmax && f <= ftest1 && dg <= dgtest) {
      info[0]=5;
    }
    if (stp[0] == lb3_1_stpmin && (f > ftest1 || dg >= dgtest)) {
      info[0]=4;
    }
    if (nfev[0] >= maxfev) {
      info[0]=3;
    }
    if (brackt && stmax - stmin <= xtol * stmax) {
      info[0]=2;
    }
    if (f <= ftest1 && Math.abs(dg) <= lb3_1_gtol * (-dginit)) {
      info[0]=1;
    }
    if (info[0] != 0) {
      return;
    }
    if (stage1 && f <= ftest1 && dg >= Math.min(ftol,lb3_1_gtol) * dginit) {
      stage1=false;
    }
    if (stage1 && f <= fx && f > ftest1) {
      double fm=f - stp[0] * dgtest;
      double fxm=fx - stx * dgtest;
      double fym=fy - sty * dgtest;
      double dgm=dg - dgtest;
      double dgxm=dgx - dgtest;
      double dgym=dgy - dgtest;
      double[] stxArr={stx};
      double[] fxmArr={fxm};
      double[] dgxmArr={dgxm};
      double[] styArr={sty};
      double[] fymArr={fym};
      double[] dgymArr={dgym};
      boolean[] bracktArr={brackt};
      int[] infocArr={infoc};
      mcstep(stxArr,fxmArr,dgxmArr,styArr,fymArr,dgymArr,stp,fm,dgm,bracktArr,stmin,stmax,infocArr);
      stx=stxArr[0];
      fxm=fxmArr[0];
      dgxm=dgxmArr[0];
      sty=styArr[0];
      fym=fymArr[0];
      dgym=dgymArr[0];
      brackt=bracktArr[0];
      infoc=infocArr[0];
      fx=fxm + stx * dgtest;
      fy=fym + sty * dgtest;
      dgx=dgxm + dgtest;
      dgy=dgym + dgtest;
    }
 else {
      double[] stxArr={stx};
      double[] fxArr={fx};
      double[] dgxArr={dgx};
      double[] styArr={sty};
      double[] fyArr={fy};
      double[] dgyArr={dgy};
      boolean[] bracktArr={brackt};
      int[] infocArr={infoc};
      mcstep(stxArr,fxArr,dgxArr,styArr,fyArr,dgyArr,stp,f,dg,bracktArr,stmin,stmax,infocArr);
      stx=stxArr[0];
      fx=fxArr[0];
      dgx=dgxArr[0];
      sty=styArr[0];
      fy=fyArr[0];
      dgy=dgyArr[0];
      brackt=bracktArr[0];
      infoc=infocArr[0];
    }
    if (brackt) {
      double d1=sty - stx;
      if (Math.abs(d1) >= p66 * width1) {
        stp[0]=stx + p5 * (sty - stx);
      }
      width1=width;
      d1=sty - stx;
      width=Math.abs(d1);
    }
    firstLoop=false;
  }
}
