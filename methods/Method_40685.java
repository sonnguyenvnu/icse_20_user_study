public Op convertOp(Object map){
  String name=(String)((Map<String,Object>)map).get("name");
  if (name.equals("+") || name.equals("+@")) {
    return Op.Add;
  }
  if (name.equals("-") || name.equals("-@") || name.equals("<=>")) {
    return Op.Sub;
  }
  if (name.equals("*")) {
    return Op.Mul;
  }
  if (name.equals("/")) {
    return Op.Div;
  }
  if (name.equals("**")) {
    return Op.Pow;
  }
  if (name.equals("=~")) {
    return Op.Match;
  }
  if (name.equals("!~")) {
    return Op.NotMatch;
  }
  if (name.equals("==") || name.equals("===")) {
    return Op.Equal;
  }
  if (name.equals("<")) {
    return Op.Lt;
  }
  if (name.equals(">")) {
    return Op.Gt;
  }
  if (name.equals("&")) {
    return Op.BitAnd;
  }
  if (name.equals("|")) {
    return Op.BitOr;
  }
  if (name.equals("^")) {
    return Op.BitXor;
  }
  if (name.equals("in")) {
    return Op.In;
  }
  if (name.equals("<<")) {
    return Op.LShift;
  }
  if (name.equals("%")) {
    return Op.Mod;
  }
  if (name.equals(">>")) {
    return Op.RShift;
  }
  if (name.equals("~")) {
    return Op.Invert;
  }
  if (name.equals("and") || name.equals("&&")) {
    return Op.And;
  }
  if (name.equals("or") || name.equals("||")) {
    return Op.Or;
  }
  if (name.equals("not") || name.equals("!")) {
    return Op.Not;
  }
  if (name.equals("!=")) {
    return Op.NotEqual;
  }
  if (name.equals("<=")) {
    return Op.LtE;
  }
  if (name.equals(">=")) {
    return Op.GtE;
  }
  if (name.equals("defined")) {
    return Op.Defined;
  }
  _.die("illegal operator: " + name);
  return null;
}
