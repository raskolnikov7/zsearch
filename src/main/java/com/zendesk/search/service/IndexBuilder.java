package com.zendesk.search.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

public interface IndexBuilder {

	public void buildIndex(File file) throws FileNotFoundException, IOException, ParseException;

}
