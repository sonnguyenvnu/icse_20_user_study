@GetMapping({"/app-mods/{page}","/app-mods/{page}/{limit}","/app-mods"}) public ListAppMods listAppMods(@PathVariable(value="page",required=false) @RequestParam(value="page",required=false) Integer page,@PathVariable(value="limit",required=false) @RequestParam(value="limit",required=false) Integer limit){
  return adminService.listAppMods(page,limit);
}
