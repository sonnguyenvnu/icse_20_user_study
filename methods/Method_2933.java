/** 
 * ??????
 */
void refresh_stack_information(){
  int sz=stack.size();
  if (0 == sz) {
    top0=-1;
    top1=-1;
  }
 else   if (1 == sz) {
    top0=stack.get(sz - 1);
    top1=-1;
  }
 else {
    top0=stack.get(sz - 1);
    top1=stack.get(sz - 2);
  }
}
