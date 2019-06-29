package com.otta.raceTest.upload.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.*;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.otta.raceTest.upload.model.Header;

@RunWith(MockitoJUnitRunner.class)
public class HeaderValidatorTest {
	private static final String[] VALID_HEADERS = { "Hora", "Piloto", "Nº", "Volta", "Tempo", "Volta", "Velocidade",
			"média", "da", "volta" };
	private static final String[] INVALID_HEADERS = { "Hora", "Piloto", "Nº", "Volta", "Tempo", "Volta", "Velocidade",
			"média", "da", "volta", "mais", "campos", "aqui" };
	private static final String[] ANOTHER_HEADERS = { "Hora", "Diferente", "Nº", "Volta", "D", "Volta", "Diferente",
			"média", "da", "volta" };

	private HeaderValidator headerValidator;

	@Mock
	private Header header;

	@Before
	public void setUp() throws Exception {
		headerValidator = new HeaderValidator();
	}

	@Test
	public void shouldReturnTrueIfHeaderIsValid() throws Exception {
		// given
		given(header.getTitles()).willReturn(Lists.list(VALID_HEADERS));
		// when
		boolean actualValue = headerValidator.validate(header);
		// then
		assertTrue(actualValue);
	}
	
	@Test
	public void shouldReturnFalseIfHeaderIsEmpty() throws Exception {
		// given
		given(header.getTitles()).willReturn(Lists.list());
		// when
		boolean actualValue = headerValidator.validate(header);
		// then
		assertFalse(actualValue);
	}
	
	@Test
	public void shouldReturnFalseIfHeaderHaveMoreValues() throws Exception {
		// given
		given(header.getTitles()).willReturn(Lists.list(INVALID_HEADERS));
		// when
		boolean actualValue = headerValidator.validate(header);
		// then
		assertFalse(actualValue);
	}

	@Test
	public void shouldReturnFalseIfHeaderHaveAnotherValues() throws Exception {
		// given
		given(header.getTitles()).willReturn(Lists.list(ANOTHER_HEADERS));
		// when
		boolean actualValue = headerValidator.validate(header);
		// then
		assertFalse(actualValue);
	}
}
