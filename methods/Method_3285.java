int lbfgs_optimize(int size,int msize,double[] x,double f,double[] g,double[] diag,double[] w,boolean orthant,double C,double[] v,double[] xi,int iflag){
  double yy=0.0;
  double ys=0.0;
  int bound=0;
  int cp=0;
  if (orthant) {
    pseudo_gradient(size,v,x,g,C);
  }
  if (mcsrch_ == null) {
    mcsrch_=new Mcsrch();
  }
  boolean firstLoop=true;
  if (iflag == 0) {
    point=0;
    for (int i=0; i < size; ++i) {
      diag[i]=1.0;
    }
    ispt=size + (msize << 1);
    iypt=ispt + size * msize;
    for (int i=0; i < size; ++i) {
      w[ispt + i]=-v[i] * diag[i];
    }
    stp1=1.0 / Math.sqrt(Mcsrch.ddot_(size,v,0,v,0));
  }
  while (true) {
    if (!firstLoop || (firstLoop && iflag != 1 && iflag != 2)) {
      ++iter;
      info=0;
      if (orthant) {
        for (int i=0; i < size; ++i) {
          xi[i]=(x[i] != 0 ? Mcsrch.sigma(x[i]) : Mcsrch.sigma(-v[i]));
        }
      }
      if (iter != 1) {
        if (iter > size)         bound=size;
        ys=Mcsrch.ddot_(size,w,iypt + npt,w,ispt + npt);
        yy=Mcsrch.ddot_(size,w,iypt + npt,w,iypt + npt);
        for (int i=0; i < size; ++i) {
          diag[i]=ys / yy;
        }
      }
    }
    if (iter != 1 && (!firstLoop || (iflag != 1 && firstLoop))) {
      cp=point;
      if (point == 0) {
        cp=msize;
      }
      w[size + cp - 1]=1.0 / ys;
      for (int i=0; i < size; ++i) {
        w[i]=-v[i];
      }
      bound=Math.min(iter - 1,msize);
      cp=point;
      for (int i=0; i < bound; ++i) {
        --cp;
        if (cp == -1) {
          cp=msize - 1;
        }
        double sq=Mcsrch.ddot_(size,w,ispt + cp * size,w,0);
        int inmc=size + msize + cp;
        iycn=iypt + cp * size;
        w[inmc]=w[size + cp] * sq;
        double d=-w[inmc];
        Mcsrch.daxpy_(size,d,w,iycn,w,0);
      }
      for (int i=0; i < size; ++i) {
        w[i]=diag[i] * w[i];
      }
      for (int i=0; i < bound; ++i) {
        double yr=Mcsrch.ddot_(size,w,iypt + cp * size,w,0);
        double beta=w[size + cp] * yr;
        int inmc=size + msize + cp;
        beta=w[inmc] - beta;
        iscn=ispt + cp * size;
        Mcsrch.daxpy_(size,beta,w,iscn,w,0);
        ++cp;
        if (cp == msize) {
          cp=0;
        }
      }
      if (orthant) {
        for (int i=0; i < size; ++i) {
          w[i]=(Mcsrch.sigma(w[i]) == Mcsrch.sigma(-v[i]) ? w[i] : 0);
        }
      }
      for (int i=0; i < size; ++i) {
        w[ispt + point * size + i]=w[i];
      }
    }
    if (!firstLoop || (firstLoop && iflag != 1)) {
      nfev=0;
      stp=1.0;
      if (iter == 1) {
        stp=stp1;
      }
      for (int i=0; i < size; ++i) {
        w[i]=g[i];
      }
    }
    double[] stpArr={stp};
    int[] infoArr={info};
    int[] nfevArr={nfev};
    mcsrch_.mcsrch(size,x,f,v,w,ispt + point * size,stpArr,infoArr,nfevArr,diag);
    stp=stpArr[0];
    info=infoArr[0];
    nfev=nfevArr[0];
    if (info == -1) {
      if (orthant) {
        for (int i=0; i < size; ++i) {
          x[i]=(Mcsrch.sigma(x[i]) == Mcsrch.sigma(xi[i]) ? x[i] : 0);
        }
      }
      return 1;
    }
    if (info != 1) {
      System.err.println("The line search routine mcsrch failed: error code:" + info);
      return -1;
    }
    npt=point * size;
    for (int i=0; i < size; ++i) {
      w[ispt + npt + i]=stp * w[ispt + npt + i];
      w[iypt + npt + i]=g[i] - w[i];
    }
    ++point;
    if (point == msize)     point=0;
    double gnorm=Math.sqrt(Mcsrch.ddot_(size,v,0,v,0));
    double xnorm=Math.max(1.0,Math.sqrt(Mcsrch.ddot_(size,x,0,x,0)));
    if (gnorm / xnorm <= Mcsrch.eps) {
      return 0;
    }
    firstLoop=false;
  }
}
