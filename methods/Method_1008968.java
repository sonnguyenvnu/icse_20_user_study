public void put(Part part){
  if (get(part.getPartName()) != null) {
    log.info("Overwriting existing part " + part.getPartName());
  }
 else {
    log.debug("put part " + part.getPartName());
  }
  parts.put(part.getPartName(),part);
}
