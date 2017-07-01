package com.zendesk.search.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

public interface IndexBuilder {

	public void buildIndex(String filePath) throws FileNotFoundException, IOException, ParseException;

}
