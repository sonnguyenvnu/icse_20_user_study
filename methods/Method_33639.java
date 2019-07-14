/** 
 * ????
 * @param position ????
 */
private void setCurrentItem(int position){
  boolean isOne=false;
  boolean isTwo=false;
  boolean isThree=false;
switch (position) {
case 0:
    isOne=true;
  break;
case 1:
isTwo=true;
break;
case 2:
isThree=true;
break;
default :
isTwo=true;
break;
}
vpContent.setCurrentItem(position);
ivTitleOne.setSelected(isOne);
ivTitleTwo.setSelected(isTwo);
ivTitleThree.setSelected(isThree);
}
