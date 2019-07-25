/** 
 * Prints to standard error the string (v1 + "  " + v2), and returns the value v2.   Modified on 22 June 2011 by LL to call deepNormalize() on the values before printing.  This fixes the same bug that caused PrintT({"a", "a"}) to print {"a", "a"} instead of {"a"}.  For safety, the values are copied before normalizing, thought that's probably not necessary.
 */
public static Value Print(Value v1,Value v2){
  Value v1c=(Value)v1.deepCopy();
  Value v2c=(Value)v2.deepCopy();
  v1c.deepNormalize();
  v2c.deepNormalize();
  if (OUTPUT == null) {
    ToolIO.out.println(Values.ppr(v1c.toStringUnchecked()) + "  " + Values.ppr(v2c.toStringUnchecked()));
  }
 else {
    try {
      OUTPUT.write(Values.ppr(v1c.toStringUnchecked()) + "  " + Values.ppr(v2c.toStringUnchecked()) + "\n");
    }
 catch (    IOException e) {
      MP.printError(EC.GENERAL,e);
    }
  }
  return v2;
}
