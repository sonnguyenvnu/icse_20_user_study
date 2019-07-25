private int max(int size){
switch (size) {
case 1:
case 2:
case 3:
case 4:
    return 10000;
case 5:
  return 100000;
case 6:
return 1000000;
case 7:
return 10000000;
case 8:
return 100000000;
case 9:
return 1000000000;
default :
return Integer.MAX_VALUE;
}
}
