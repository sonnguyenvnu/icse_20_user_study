protected BitVectorWrapper reassign(Exception e) throws Exception {
  ToolIO.out.println("Warning: Failed to connect from " + fpSetManager.getHostName() + " to the fp server at " + fpset.get(index).getHostname() + ".\n" + e.getMessage());
  if (fpSetManager.reassign(index) == -1) {
    ToolIO.out.println("Warning: there is no fp server available.");
    return new BitVectorWrapper(index,new BitVector(fps[index].size(),true));
  }
 else {
    return call();
  }
}
