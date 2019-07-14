/** 
 * Converts a String representation of a clock value into the float representation used in this API. <p> Clock values have the following syntax: </p> <p> <pre> Clock-val         ::= ( Full-clock-val | Partial-clock-val | Timecount-val ) Full-clock-val    ::= Hours ":" Minutes ":" Seconds ("." Fraction)? Partial-clock-val ::= Minutes ":" Seconds ("." Fraction)? Timecount-val     ::= Timecount ("." Fraction)? (Metric)? Metric            ::= "h" | "min" | "s" | "ms" Hours             ::= DIGIT+; any positive number Minutes           ::= 2DIGIT; range from 00 to 59 Seconds           ::= 2DIGIT; range from 00 to 59 Fraction          ::= DIGIT+ Timecount         ::= DIGIT+ 2DIGIT            ::= DIGIT DIGIT DIGIT             ::= [0-9] </pre>
 * @param clockValue A String in the representation specified above
 * @return  A float value in milliseconds that matches the stringrepresentation given as the parameter
 * @exception IllegalArgumentException if the clockValue inputparameter does not comply with the defined syntax
 * @exception NullPointerException if the clockValue string is<code>null</code>
 */
public static float parseClockValue(String clockValue){
  try {
    float result=0;
    clockValue=clockValue.trim();
    if (clockValue.endsWith("ms")) {
      result=parseFloat(clockValue,2,true);
    }
 else     if (clockValue.endsWith("s")) {
      result=1000 * parseFloat(clockValue,1,true);
    }
 else     if (clockValue.endsWith("min")) {
      result=60000 * parseFloat(clockValue,3,true);
    }
 else     if (clockValue.endsWith("h")) {
      result=3600000 * parseFloat(clockValue,1,true);
    }
 else {
      try {
        return parseFloat(clockValue,0,true) * 1000;
      }
 catch (      NumberFormatException e) {
      }
      String[] timeValues=clockValue.split(":");
      int indexOfMinutes;
      if (timeValues.length == 2) {
        indexOfMinutes=0;
      }
 else       if (timeValues.length == 3) {
        result=3600000 * (int)parseFloat(timeValues[0],0,false);
        indexOfMinutes=1;
      }
 else {
        throw new IllegalArgumentException();
      }
      int minutes=(int)parseFloat(timeValues[indexOfMinutes],0,false);
      if ((minutes >= 00) && (minutes <= 59)) {
        result+=60000 * minutes;
      }
 else {
        throw new IllegalArgumentException();
      }
      float seconds=parseFloat(timeValues[indexOfMinutes + 1],0,true);
      if ((seconds >= 00) && (seconds < 60)) {
        result+=60000 * seconds;
      }
 else {
        throw new IllegalArgumentException();
      }
    }
    return result;
  }
 catch (  NumberFormatException e) {
    throw new IllegalArgumentException();
  }
}
