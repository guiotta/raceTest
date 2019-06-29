package com.otta.raceTest.upload.controller;

import static org.mockito.BDDMockito.*;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.otta.raceTest.bestlap.model.BestLap;
import com.otta.raceTest.bestlap.service.BestLapService;
import com.otta.raceTest.result.model.RaceResult;
import com.otta.raceTest.timedelay.model.EndRaceDelay;
import com.otta.raceTest.timedelay.service.TimeDelayService;
import com.otta.raceTest.upload.service.UploadService;

@RunWith(MockitoJUnitRunner.class)
public class UploadControllerTest {
	private static final String RESULTS_VIEW_NAME = "results";
	private static final String RACE_RESULTS_PARAM_NAME = "raceResults";
	private static final String BEST_LAP_PARAM_NAME = "bestLaps";
	private static final String END_DELAY_PARAM_NAME = "endDelayTimes";
	
	@Mock
	private BestLapService bestLapService;

	@Mock
	private TimeDelayService timeDelayService;

	@Mock
	private UploadService uploadService;

	private UploadController uploadController;

	@Mock
	private ModelAndView modelAndView;
	@Mock
	private MultipartFile file;
	@Mock
	private RaceResult raceResult;
	@Mock
	private BestLap bestLap;
	@Mock
	private EndRaceDelay endRaceDelay;

	@Before
	public void setUp() {
		uploadController = spy(new UploadController(uploadService, bestLapService, timeDelayService));

		doReturn(modelAndView).when(uploadController).createModelAndView();
		doReturn(Lists.list(raceResult)).when(uploadService).convertFileToRaceResult(file);
		doReturn(Lists.list(bestLap)).when(bestLapService).convertFileDataToBestLap(file);
		doReturn(Lists.list(endRaceDelay)).when(timeDelayService).convertToEndRaceDelay(file);
	}

	@Test
	public void shouldCorrectlyCreateModelAndView() {
		// given
		// when
		uploadController.handleFileUpload(file);
		// then
		verify(modelAndView).addObject(RACE_RESULTS_PARAM_NAME, Lists.list(raceResult));
		verify(modelAndView).addObject(BEST_LAP_PARAM_NAME, Lists.list(bestLap));
		verify(modelAndView).addObject(END_DELAY_PARAM_NAME, Lists.list(endRaceDelay));
		verify(modelAndView).setViewName(RESULTS_VIEW_NAME);
	}

}
