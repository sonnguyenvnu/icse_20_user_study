private static View assertIsView(Object mountContent,AnimatedProperty property){
  if (!(mountContent instanceof View)) {
    throw new RuntimeException("Animating '" + property.getName() + "' is only supported on Views (got " + mountContent + ")");
  }
  return (View)mountContent;
}
