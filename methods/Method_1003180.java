private void balance(TreeNode x,boolean isLeft){
  while (true) {
    int sign=isLeft ? 1 : -1;
switch (x.balance * sign) {
case 1:
      x.balance=0;
    return;
case 0:
  x.balance=-sign;
break;
case -1:
TreeNode l=child(x,isLeft);
if (l.balance == -sign) {
replace(x,l);
set(x,isLeft,child(l,!isLeft));
set(l,!isLeft,x);
x.balance=0;
l.balance=0;
}
 else {
TreeNode r=child(l,!isLeft);
replace(x,r);
set(l,!isLeft,child(r,isLeft));
set(r,isLeft,l);
set(x,isLeft,child(r,!isLeft));
set(r,!isLeft,x);
int rb=r.balance;
x.balance=(rb == -sign) ? sign : 0;
l.balance=(rb == sign) ? -sign : 0;
r.balance=0;
}
return;
default :
DbException.throwInternalError("b:" + x.balance * sign);
}
if (x == root) {
return;
}
isLeft=x.isFromLeft();
x=x.parent;
}
}
