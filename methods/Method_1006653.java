@PreAuthorize("hasRole('STORE_ADMIN')") @RequestMapping(value="/admin/group/save.html",method=RequestMethod.POST) public String save(@Valid @ModelAttribute("group") GroupDetails group,BindingResult result,Locale locale,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
  setMenu(model,request);
  GroupType[] groupTypes=GroupType.values();
  List<String> groups=new ArrayList<String>();
  for (  GroupType t : groupTypes) {
    if (GroupType.ADMIN.name() != t.name()) {
      groups.add(t.name());
    }
  }
  Group g=null;
  if (group.getGroup().getId() != null) {
    Group gid=groupService.getById(group.getGroup().getId());
    if (gid != null && !gid.getGroupName().equals(group.getGroup().getGroupName())) {
      g=groupService.findByName(group.getGroup().getGroupName());
    }
  }
 else {
    g=groupService.findByName(group.getGroup().getGroupName());
  }
  if (g != null) {
    ObjectError error=new ObjectError("group.groupName",messages.getMessage("message.name.exist",locale));
    result.addError(error);
    model.addAttribute("error","error");
  }
 else {
    groupService.save(group.getGroup());
    model.addAttribute("success","success");
  }
  GroupDetails groupDetails=new GroupDetails();
  groupDetails.setGroup(group.getGroup());
  groupDetails.setTypes(groups);
  model.addAttribute("group",groupDetails);
  return "admin-user-group";
}
