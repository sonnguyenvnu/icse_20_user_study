public String[] generate(int Samples,int Rate){
  int STATE=M0S;
  String result[]=new String[Samples];
  dbg("Entering generate");
  samples=1000;
  if (Samples > 0)   samples=Samples;
  for (i=0; i < samples; i++)   result[i]=new String();
  rate=0;
  if (Rate > 0)   rate=Rate;
  if (rate > 2)   rate=2;
  initialize();
  dbg("Entering state machine");
  sam1=-1;
  while (STATE != END) {
switch (STATE) {
case M0S:
      sam1++;
    if (sam1 >= samples) {
      STATE=END;
      break;
    }
case M0:
  for (i=1; i <= 81; i++)   A[i]=0;
part=0;
q7=0;
case MR1:
i1=(int)((MWC() >> 8) & 127);
if (i1 > 80) {
STATE=MR1;
break;
}
i1++;
if (A[i1] > 0) {
STATE=MR1;
break;
}
case MR3:
s=(int)((MWC() >> 9) & 15);
if (s > 8) {
STATE=MR3;
break;
}
s++;
A[i1]=s;
m2=solve();
q7++;
if (m2 < 1) A[i1]=0;
if (m2 != 1) {
STATE=MR1;
break;
}
part++;
if (solve() != 1) {
STATE=M0;
break;
}
case MR4:
for (i=1; i <= 81; i++) {
x=(int)((MWC() >> 8) & 127);
while (x >= i) {
x=(int)((MWC() >> 8) & 127);
}
x++;
P[i]=P[x];
P[x]=i;
}
for (i1=1; i1 <= 81; i1++) {
s1=A[P[i1]];
A[P[i1]]=0;
if (solve() > 1) A[P[i1]]=s1;
}
if (rate > 0) {
nt=0;
mi1=9999;
for (f=0; f < 100; f++) {
solve();
nt+=nodes;
if (nodes < mi1) {
mi1=nodes;
mi2=C[clues];
}
}
result[sam1]=result[sam1].concat("Rating:" + nt + "# ");
if (rate > 1) {
result[sam1]=result[sam1].concat("hint: " + String.valueOf(H[mi2]).substring(0,4) + " #\n");
}
 else result[sam1]=result[sam1].concat("\n");
}
for (i=1; i <= 81; i++) {
result[sam1]=result[sam1].concat(String.valueOf(L[A[i]]));
if (i % 9 == 0) {
result[sam1]=result[sam1].concat("\n");
}
}
result[sam1]=result[sam1].concat("\n");
default :
dbg("Default case. New state M0S");
STATE=M0S;
break;
}
}
return result;
}
