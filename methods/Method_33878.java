@RequestMapping("/google/picasa") public String photos(Model model) throws Exception {
  model.addAttribute("photoUrls",googleService.getLastTenPicasaPictureURLs());
  return "picasa";
}
