private static void recurse(StringBuilder sb,FieldRef fr,String indent){
  for (  Object o : fr.getInstructions()) {
    if (o instanceof FieldRef) {
      recurse(sb,((FieldRef)o),indent + "    ");
    }
 else {
      o=XmlUtils.unwrap(o);
      if (o instanceof Text) {
        String instr=((Text)o).getValue();
        sb.append("\n" + indent + instr);
      }
 else {
        sb.append("\n" + indent + XmlUtils.unwrap(o).getClass().getName());
      }
    }
  }
}
