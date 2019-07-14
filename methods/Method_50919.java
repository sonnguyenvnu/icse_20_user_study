public String getPhaseText(int phase){
switch (phase) {
case CPDListener.INIT:
    return "Initializing";
case CPDListener.HASH:
  return "Hashing";
case CPDListener.MATCH:
return "Matching";
case CPDListener.GROUPING:
return "Grouping";
case CPDListener.DONE:
return "Done";
default :
return "Unknown";
}
}
