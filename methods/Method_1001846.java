int solve(){
  int STATE=M2;
  for (i=0; i <= n; i++)   Ur[i]=0;
  for (i=0; i <= m; i++)   Uc[i]=0;
  clues=0;
  for (i=1; i <= 81; i++)   if (A[i] > 0) {
    clues++;
    r=i * 9 - 9 + A[i];
    for (j=1; j <= Cols[r]; j++) {
      d=Col[r][j];
      if (Uc[d] > 0)       return 0;
      Uc[d]++;
      for (k=1; k <= Rows[d]; k++) {
        Ur[Row[d][k]]++;
      }
    }
  }
  for (c=1; c <= m; c++) {
    V[c]=0;
    for (r=1; r <= Rows[c]; r++)     if (Ur[Row[c][r]] == 0)     V[c]++;
  }
  i=clues;
  m0=0;
  m1=0;
  solutions=0;
  nodes=0;
  dbg("Solve: Entering state machine");
  while (STATE != END) {
switch (STATE) {
case M2:
      i++;
    I[i]=0;
  min=n + 1;
if ((i > 81) || (m0 > 0)) {
  STATE=M4;
  break;
}
if (m1 > 0) {
C[i]=m1;
STATE=M3;
break;
}
w=0;
for (c=1; c <= m; c++) if (Uc[c] == 0) {
if (V[c] < 2) {
C[i]=c;
STATE=M3;
break;
}
if (V[c] <= min) {
w++;
W[(int)w]=c;
}
;
if (V[c] < min) {
w=1;
W[(int)w]=c;
min=V[c];
}
}
if (STATE == M3) {
break;
}
case MR:
c2=(MWC() & Two[(int)w]);
while (c2 >= w) {
c2=(MWC() & Two[(int)w]);
}
C[i]=W[(int)c2 + 1];
case M3:
c=C[i];
I[i]++;
if (I[i] > Rows[c]) {
STATE=M4;
break;
}
r=Row[c][I[i]];
if (Ur[r] > 0) {
STATE=M3;
break;
}
m0=0;
m1=0;
nodes++;
for (j=1; j <= Cols[r]; j++) {
c1=Col[r][j];
Uc[c1]++;
}
for (j=1; j <= Cols[r]; j++) {
c1=Col[r][j];
for (k=1; k <= Rows[c1]; k++) {
r1=Row[c1][k];
Ur[r1]++;
if (Ur[r1] == 1) for (l=1; l <= Cols[r1]; l++) {
c2=Col[r1][l];
V[(int)c2]--;
if (Uc[(int)c2] + V[(int)c2] < 1) m0=(int)c2;
if (Uc[(int)c2] == 0 && V[(int)c2] < 2) m1=(int)c2;
}
}
}
if (i == 81) solutions++;
if (solutions > 1) {
STATE=M9;
break;
}
STATE=M2;
break;
case M4:
i--;
if (i == clues) {
STATE=M9;
break;
}
c=C[i];
r=Row[c][I[i]];
for (j=1; j <= Cols[r]; j++) {
c1=Col[r][j];
Uc[c1]--;
for (k=1; k <= Rows[c1]; k++) {
r1=Row[c1][k];
Ur[r1]--;
if (Ur[r1] == 0) for (l=1; l <= Cols[r1]; l++) {
c2=Col[r1][l];
V[(int)c2]++;
}
}
}
if (i > clues) {
STATE=M3;
break;
}
case M9:
STATE=END;
break;
default :
STATE=END;
break;
}
}
return solutions;
}
