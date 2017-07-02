package com.zendesk.search.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.zendesk.search.util.Constants;

@Component
public class CommandLineAppRunner implements CommandLineRunner {

	@Autowired
	private SearchService searchService;

	@Override
	public void run(String... arg0) throws Exception {

		searchService.buildIndexes();
		cli();

	}

	private void cli() {
		BufferedReader br = null;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));

			while (true) {

				System.out.print("*Welcome to Zendesk Search*");
				System.out.println("Select search options:");
				System.out.println("Press 1 to search from a repository");
				System.out.println("Press 2 to view list of searchable fields in all repositories");
				System.out.println("Type 'quit' to exit");

				String input = br.readLine();

				if ("quit".equals(input)) {
					System.out.println("Exit!");
					System.exit(0);
				}
				int repositoryType = 0;
				System.out.println("Input:" + input);
				int searchType = Integer.parseInt(input);
				if (searchType == 1) {
					System.out.println("Please select the search repository");
					System.out.println("1 - User, 2 - Tickets, 3 - Organization");
					String repo = br.readLine();
					if ((Integer.parseInt(repo) >= 1 && Integer.parseInt(repo) <= 3)) {
						repositoryType = Integer.parseInt(repo);
						System.out.println("You selcted: " + repo);
						System.out.println("Enter search term: ");
						String searchTerm = br.readLine();

						System.out.println("Enter search value: ");
						String searchValue = br.readLine();
						String result = searchService.executeSearch(repositoryType, searchTerm, searchValue);
						System.out.println(result);
					}

				} else if (searchType == 2) {
					searchService.listSearchTerms();
				} else {
					System.out.println(Constants.INVALID_CHOICE);
				}
				System.out.println("-----------\n");
			}

		} catch (NumberFormatException e) {
			System.out.println(Constants.INVALID_CHOICE);
			cli();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
