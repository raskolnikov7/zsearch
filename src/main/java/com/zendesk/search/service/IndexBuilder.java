package com.zendesk.search.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.json.simple.parser.ParseException;

public interface IndexBuilder {

	public void buildIndex(InputStream stream) throws FileNotFoundException, IOException, ParseException;

}
