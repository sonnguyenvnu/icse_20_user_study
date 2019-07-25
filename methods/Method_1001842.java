/** 
 * Helper function.
 */
int shuffle(){
  for (i=1; i <= m; i++) {
    a=(int)((MWC() >> 8) & Mc[N]);
    while (a >= i)     a=(int)((MWC() >> 8) & Mc[N]);
    a++;
    P[i]=P[a];
    P[a]=i;
  }
  for (c=1; c <= m; c++) {
    Rows[c]=0;
    T[c]=Uc[c];
  }
  for (c=1; c <= m; c++)   Uc[P[c]]=T[c];
  for (r=1; r <= n; r++)   for (i=1; i <= Cols[r]; i++) {
    c=P[Col[r][i]];
    Col[r][i]=c;
    Rows[c]++;
    Row[c][Rows[c]]=r;
  }
  for (i=1; i <= n; i++) {
    a=(int)((MWC() >> 8) & Mr[N]);
    while (a >= i)     a=(int)((MWC() >> 8) & Mr[N]);
    a++;
    P[i]=P[a];
    P[a]=i;
  }
  for (r=1; r <= n; r++) {
    Cols[r]=0;
    T[r]=Ur[r];
  }
  for (r=1; r <= n; r++)   Ur[P[r]]=T[r];
  for (c=1; c <= m; c++)   for (i=1; i <= Rows[c]; i++) {
    r=P[Row[c][i]];
    Row[c][i]=r;
    Cols[r]++;
    Col[r][Cols[r]]=c;
  }
  for (r=1; r <= n; r++) {
    for (i=1; i <= Cols[r]; i++) {
      a=(int)((MWC() >> 8) & 7);
      while (a >= i)       a=(int)((MWC() >> 8) & 7);
      a++;
      P[i]=P[a];
      P[a]=i;
    }
    for (i=1; i <= Cols[r]; i++)     T[i]=Col[r][P[i]];
    for (i=1; i <= Cols[r]; i++)     Col[r][i]=T[i];
  }
  for (c=1; c <= m; c++) {
    for (i=1; i <= Rows[c]; i++) {
      a=(int)((MWC() >> 8) & Mw[N]);
      while (a >= i)       a=(int)((MWC() >> 8) & Mw[N]);
      a++;
      P[i]=P[a];
      P[a]=i;
    }
    for (i=1; i <= Rows[c]; i++)     T[i]=Row[c][P[i]];
    for (i=1; i <= Rows[c]; i++)     Row[c][i]=T[i];
  }
  return 0;
}
