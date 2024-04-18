package com.hahnentt.Issue1178.reddeer;

import org.eclipse.reddeer.common.wait.WaitUntil;
import org.eclipse.reddeer.eclipse.condition.ConsoleHasLabel;
import org.eclipse.reddeer.eclipse.ui.console.ConsoleView;
import org.eclipse.reddeer.junit.runner.RedDeerSuite;
import org.eclipse.reddeer.requirements.cleanworkspace.CleanWorkspaceRequirement.CleanWorkspace;
import org.eclipse.reddeer.requirements.closeeditors.CloseAllEditorsRequirement.CloseAllEditors;
import org.eclipse.reddeer.swt.impl.menu.ToolItemMenuItem;
import org.eclipse.reddeer.swt.impl.toolbar.DefaultToolItem;
import org.hamcrest.core.StringEndsWith;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RedDeerSuite.class)
@CleanWorkspace
@CloseAllEditors
public class ReproducerTest {
	@Test
	public void test_change_console() {
		var consoleView = new ConsoleView();
		consoleView.open();
		
		// Open SonarLint console
		var matcher = StringEndsWith.endsWith("SonarLint Console");
		var menu = new ToolItemMenuItem(new DefaultToolItem(consoleView.getCTabItem().getFolder(), "Open Console"), matcher);
		menu.select();
		new WaitUntil(new ConsoleHasLabel(matcher));
		
		// Enable verbose output
		consoleView.activate();
		// INFO: When doing a break-point here on the next line and then resuming from it, it works!
		var menu2 = new ToolItemMenuItem(new DefaultToolItem(consoleView.getCTabItem().getFolder(), "Configure logs"), "Verbose output");
	    if (!menu2.isSelected()) {
	      menu2.select();
	    }
	}
}
