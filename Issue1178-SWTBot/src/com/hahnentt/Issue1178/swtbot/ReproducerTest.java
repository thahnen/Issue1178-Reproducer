package com.hahnentt.Issue1178.swtbot;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.junit.Test;

public class ReproducerTest {
	private static final SWTWorkbenchBot bot = new SWTWorkbenchBot();
	
	@Test
	public void test_change_console() {
		// given
	    UIThreadRunnable.syncExec(new VoidResult() {
			@Override
			public void run() {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					  .showView("org.eclipse.ui.console.ConsoleView");
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		});
		
		var consoleView = bot.viewById("org.eclipse.ui.console.ConsoleView");
		consoleView.show();
		consoleView.setFocus();
		
		// Open SonarLint console
		consoleView.toolbarDropDownButton("Open Console").menuItem(WidgetMatcherFactory.withRegex(".*SonarLint.*")).click();
		consoleView.show();
		consoleView.setFocus();
		
		// Enable verbose output
		// INFO: When doing a break-point here on the next line and then resuming from it, it works!
		var menu = consoleView.toolbarDropDownButton("Configure logs").menuItem("Verbose output");
		if (!menu.isChecked()) {
			menu.click();
		}
	}
}
