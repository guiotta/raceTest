package com.otta.raceTest.upload.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.otta.raceTest.bestlap.model.BestLap;
import com.otta.raceTest.bestlap.service.BestLapService;
import com.otta.raceTest.result.model.RaceResult;
import com.otta.raceTest.timedelay.model.EndRaceDelay;
import com.otta.raceTest.timedelay.service.TimeDelayService;
import com.otta.raceTest.upload.service.UploadService;

@Controller
public class UploadController {
	private static final String RACE_RESULTS_PARAM_NAME = "raceResults";
	private static final String BEST_LAP_PARAM_NAME = "bestLaps";
	private static final String END_DELAY_PARAM_NAME = "endDelayTimes";
	
	private final UploadService uploadService;
	private final BestLapService bestLapService;
	private final TimeDelayService timeDelayService;

	@Autowired
	public UploadController(UploadService uploadService, BestLapService bestLapService,
			TimeDelayService timeDelayService) {
		this.uploadService = uploadService;
		this.bestLapService = bestLapService;
		this.timeDelayService = timeDelayService;
	}

	@GetMapping("/")
	public String listUploadedFiles(Model model) {

		return "home";
	}

	@PostMapping("/")
	public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		ModelAndView modelAndView = new ModelAndView();
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		Collection<RaceResult> raceResultCollection = uploadService.convertFileToRaceResult(file);
		Collection<BestLap> bestLapCollection = bestLapService.convertFileDataToBestLap(file);
		Collection<EndRaceDelay> endRaceDelayCollection = timeDelayService.convertToEndRaceDelay(file);
		modelAndView.addObject(RACE_RESULTS_PARAM_NAME, raceResultCollection);
		modelAndView.addObject(BEST_LAP_PARAM_NAME, bestLapCollection);
		modelAndView.addObject(END_DELAY_PARAM_NAME, endRaceDelayCollection);
		modelAndView.setViewName("results");

		return modelAndView;
	}

}
