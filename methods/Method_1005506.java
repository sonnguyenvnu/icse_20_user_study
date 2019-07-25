private void select(String[] selectedValue){
switch (curStatus) {
case 0:
    pickerViewAlone.setSelectValue(selectedValue);
  break;
case 1:
pickerViewLinkage.setSelectValue(selectedValue);
break;
}
}
