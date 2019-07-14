private void handleG2Character(int characterCode){
switch (characterCode) {
case CHARACTER_TSP:
    currentCueBuilder.append('\u0020');
  break;
case CHARACTER_NBTSP:
currentCueBuilder.append('\u00A0');
break;
case CHARACTER_ELLIPSIS:
currentCueBuilder.append('\u2026');
break;
case CHARACTER_BIG_CARONS:
currentCueBuilder.append('\u0160');
break;
case CHARACTER_BIG_OE:
currentCueBuilder.append('\u0152');
break;
case CHARACTER_SOLID_BLOCK:
currentCueBuilder.append('\u2588');
break;
case CHARACTER_OPEN_SINGLE_QUOTE:
currentCueBuilder.append('\u2018');
break;
case CHARACTER_CLOSE_SINGLE_QUOTE:
currentCueBuilder.append('\u2019');
break;
case CHARACTER_OPEN_DOUBLE_QUOTE:
currentCueBuilder.append('\u201C');
break;
case CHARACTER_CLOSE_DOUBLE_QUOTE:
currentCueBuilder.append('\u201D');
break;
case CHARACTER_BOLD_BULLET:
currentCueBuilder.append('\u2022');
break;
case CHARACTER_TM:
currentCueBuilder.append('\u2122');
break;
case CHARACTER_SMALL_CARONS:
currentCueBuilder.append('\u0161');
break;
case CHARACTER_SMALL_OE:
currentCueBuilder.append('\u0153');
break;
case CHARACTER_SM:
currentCueBuilder.append('\u2120');
break;
case CHARACTER_DIAERESIS_Y:
currentCueBuilder.append('\u0178');
break;
case CHARACTER_ONE_EIGHTH:
currentCueBuilder.append('\u215B');
break;
case CHARACTER_THREE_EIGHTHS:
currentCueBuilder.append('\u215C');
break;
case CHARACTER_FIVE_EIGHTHS:
currentCueBuilder.append('\u215D');
break;
case CHARACTER_SEVEN_EIGHTHS:
currentCueBuilder.append('\u215E');
break;
case CHARACTER_VERTICAL_BORDER:
currentCueBuilder.append('\u2502');
break;
case CHARACTER_UPPER_RIGHT_BORDER:
currentCueBuilder.append('\u2510');
break;
case CHARACTER_LOWER_LEFT_BORDER:
currentCueBuilder.append('\u2514');
break;
case CHARACTER_HORIZONTAL_BORDER:
currentCueBuilder.append('\u2500');
break;
case CHARACTER_LOWER_RIGHT_BORDER:
currentCueBuilder.append('\u2518');
break;
case CHARACTER_UPPER_LEFT_BORDER:
currentCueBuilder.append('\u250C');
break;
default :
Log.w(TAG,"Invalid G2 character: " + characterCode);
}
}
