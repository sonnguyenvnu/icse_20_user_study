public float getRepeatCount(){
  String repeatCount=mSmilElement.getAttribute("repeatCount");
  try {
    float value=Float.parseFloat(repeatCount);
    if (value > 0) {
      return value;
    }
 else {
      return 0;
    }
  }
 catch (  NumberFormatException e) {
    return 0;
  }
}
