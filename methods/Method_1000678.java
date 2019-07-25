public boolean match(float n){
  if (l == '[' && r == ']') {
    return ir.inon(n);
  }
 else   if (l == '(' && r == ')') {
    return ir.in(n);
  }
 else   if (l == '[') {
    return ir.linon(n);
  }
  return ir.rinon(n);
}
