package com.techelevator;

import com.techelevator.util.SalesReport;
import com.techelevator.util.TELog;
import com.techelevator.view.Menu;

import java.io.IOException;
import java.util.Date;

/** VendingMachineCLI.java
 * 		1. Instantiates a menu object and a vending machine calling that menu object.
 * 		2. Run method passes menu options to menu.getChoiceFromOptions().
 * 		3. Menu returns user's choice and this vending machine then instantiates a class
 * 			and calls the respective methods within that new class:
 * 			Case 1: Inventory and displayInvetory()
 * 			Case 2: Purchase with its own instantiated menu object and then run the object
 * 			Case 3: Exits the program
 * 			Case 4: (Hidden) Calls SalesReport.java, which generates a report
 */
public class VendingMachineCLI {
	/** PROPERTIES */
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_SALES_REPORT = "";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT };
	private final Menu menu;
	private static final Date date = new Date();

	/** CONSTRUCTOR */
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public static void main(String[] args) throws IOException {
		System.out.println("\n=========================\n"
				+ "  \33[34;1mTHE UMBRELLA ACADEMY\33[0m\n"
				+ "          MENU\n"
				+ "-------------------------");

		logLaunchComments(args);								// Log: successful entry into the program
		Menu menu = new Menu(System.in, System.out);			// Instantiates a Menu object: for options
		VendingMachineCLI cli = new VendingMachineCLI(menu);	// Instantiates VendingMachineCLI: receiving Menu return options
		cli.run();												// Calls run() method
	}

	public void run() throws IOException {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			switch (choice) {
				case MAIN_MENU_OPTION_DISPLAY_ITEMS: 			// display vending machine items (call Inventory.java)
					Inventory inventory = new Inventory();		// Instantiates Inventory object: to access .csv file
					inventory.displayInventory();				// Reads/formats/displays .csv file
					break;
				case MAIN_MENU_OPTION_PURCHASE: 				// do purchase (call Purchase.java)
					Purchase purchase = new Purchase(menu);
					purchase.run();
					break;
				case MAIN_MENU_OPTION_EXIT:	// Exit the program
					System.out.println("Thank you for shopping the Vendo-Matic 800!");
					TELog.log("Exiting program with System.exit(status: 0): Process ended successfully.");
					System.exit(0);
				case (MAIN_MENU_OPTION_SALES_REPORT): // generate a sales report (call SalesReport.java)
					/** TODO: OPTIONAL SALES REPORT CODE
					 Provide a "Hidden" menu option on the main menu ("4") that writes to a sales report that shows
					 the total sales since the machine was started. The name of the file must include the date and
					 time so each sales report is uniquely named.
					 */
					SalesReport.log("***** Vendo-Matic 800 Log File *****");
					SalesReport.displaySalesReport();
					System.exit(0);   // break;
			}
		}
	}

	/** METHODS */
	private static void logLaunchComments(String[] args) throws IOException {
		TELog.log("Process successfully started: psvm() accessed without incident.");
	}

}

