public void properties(Action<Map<String,Object>> action){
  propertyActions.add(Objects.requireNonNull(action));
}
