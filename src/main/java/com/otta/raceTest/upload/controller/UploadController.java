package com.otta.raceTest.upload.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.otta.raceTest.upload.converter.UploadFileConverter;

@Controller
public class UploadController {
	@Autowired
	private UploadFileConverter lapConverter;
	
	@GetMapping("/")
    public String listUploadedFiles(Model model) {

        return "home";
    }
	
	@PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) throws IOException {

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        lapConverter.convert(file);

        return "redirect:/";
    }

}
