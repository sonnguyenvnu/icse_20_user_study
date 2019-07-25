public void undo(){
switch (prevSpeed) {
case CeilingFan.HIGH:
    ceilingFan.high();
  break;
case CeilingFan.MEDIUM:
ceilingFan.medium();
break;
case CeilingFan.LOW:
ceilingFan.low();
break;
default :
ceilingFan.off();
break;
}
}
