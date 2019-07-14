@DeleteMapping(path="/notifications/filters/{id}") public ResponseEntity<Void> deleteFilter(@PathVariable("id") String id){
  NotificationFilter deleted=filteringNotifier.removeFilter(id);
  if (deleted != null) {
    return ResponseEntity.ok().build();
  }
 else {
    return ResponseEntity.notFound().build();
  }
}
