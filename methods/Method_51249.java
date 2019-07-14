@Override public void addExample(String example){
  if (!contains(super.getExamples(),example)) {
    if (examples == null) {
      examples=new ArrayList<>(1);
    }
    examples.clear();
    examples.add(example);
    super.addExample(example);
  }
}
