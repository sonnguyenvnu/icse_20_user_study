public Op convertOp(Object map){
  String type=(String)((Map<String,Object>)map).get("type");
  if (type.equals("Add") || type.equals("UAdd")) {
    return Op.Add;
  }
  if (type.equals("Sub") || type.equals("USub")) {
    return Op.Sub;
  }
  if (type.equals("Mult")) {
    return Op.Mul;
  }
  if (type.equals("Div")) {
    return Op.Div;
  }
  if (type.equals("Pow")) {
    return Op.Pow;
  }
  if (type.equals("Eq")) {
    return Op.Equal;
  }
  if (type.equals("Is")) {
    return Op.Eq;
  }
  if (type.equals("Lt")) {
    return Op.Lt;
  }
  if (type.equals("Gt")) {
    return Op.Gt;
  }
  if (type.equals("BitAnd")) {
    return Op.BitAnd;
  }
  if (type.equals("BitOr")) {
    return Op.BitOr;
  }
  if (type.equals("BitXor")) {
    return Op.BitXor;
  }
  if (type.equals("In")) {
    return Op.In;
  }
  if (type.equals("LShift")) {
    return Op.LShift;
  }
  if (type.equals("FloorDiv")) {
    return Op.FloorDiv;
  }
  if (type.equals("Mod")) {
    return Op.Mod;
  }
  if (type.equals("RShift")) {
    return Op.RShift;
  }
  if (type.equals("Invert")) {
    return Op.Invert;
  }
  if (type.equals("And")) {
    return Op.And;
  }
  if (type.equals("Or")) {
    return Op.Or;
  }
  if (type.equals("Not")) {
    return Op.Not;
  }
  if (type.equals("NotEq")) {
    return Op.NotEqual;
  }
  if (type.equals("IsNot")) {
    return Op.NotEq;
  }
  if (type.equals("LtE")) {
    return Op.LtE;
  }
  if (type.equals("GtE")) {
    return Op.GtE;
  }
  if (type.equals("NotIn")) {
    return Op.NotIn;
  }
  _.die("illegal operator: " + type);
  return null;
}
