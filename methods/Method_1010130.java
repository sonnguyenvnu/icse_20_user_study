public void store(int majorStep,int minor,TransientModelWithMetainfo model){
  try {
    while (majorStep >= mySteps.size()) {
      mySteps.add(0);
    }
    int minorStep=mySteps.get(majorStep);
    mySteps.set(majorStep,minorStep + 1);
    assert minor == minorStep;
    ModelOutputStream os=new ModelOutputStream(myCacheContainer.createStream(getStorageName(majorStep,minorStep)));
    try {
      model.save(os);
    }
  finally {
      os.close();
    }
  }
 catch (  IOException e) {
    isOk=false;
  }
}
