@PostMapping public void processUpload(@RequestParam MultipartFile file,Model model){
  model.addAttribute("message","File '" + file.getOriginalFilename() + "' uploaded successfully");
}
